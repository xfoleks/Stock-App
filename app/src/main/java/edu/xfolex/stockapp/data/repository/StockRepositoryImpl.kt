package edu.xfolex.stockapp.data.repository

import edu.xfolex.stockapp.data.csv.CSVParser
import edu.xfolex.stockapp.data.local.database.StockDatabase
import edu.xfolex.stockapp.data.mappers.toCompanyListing
import edu.xfolex.stockapp.data.mappers.toCompanyListingEntity
import edu.xfolex.stockapp.data.remote.api.StockMarketApi
import edu.xfolex.stockapp.domain.model.CompanyListing
import edu.xfolex.stockapp.domain.repository.StockRepository
import edu.xfolex.stockapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockMarketApi,
    val database: StockDatabase,
    val companyListingParser: CSVParser<CompanyListing>
) : StockRepository {

    private val dao = database.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> = flow {
        emit(Resource.Loading(true))
        val localListings = dao.searchCompanyListing(query)
        emit(Resource.Success(
            data = localListings.map { it.toCompanyListing() }
        ))

        val isDatabaseEmpty = localListings.isEmpty() && query.isBlank()
        val shouldLoadFromCache = !isDatabaseEmpty && !fetchFromRemote
        if (shouldLoadFromCache) {
            emit(Resource.Loading(false))
            return@flow
        }
        val remoteListings = try {
            val response = api.getListings()
            companyListingParser.parse(response.byteStream())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            emit(Resource.Error("Couldn't load data"))
            null
        } catch (httpException: HttpException) {
            httpException.printStackTrace()
            emit(Resource.Error("Couldn't load data"))
            null
        }

        remoteListings?.let { listings ->
            dao.clearCompanyListings()
            dao.insertCompanyListings(
                listings.map {it.toCompanyListingEntity()}
            )
            emit(Resource.Success(
                data = dao.searchCompanyListing("")
                    .map { it.toCompanyListing() }
            ))
            emit(Resource.Loading(false))
        }
    }
}