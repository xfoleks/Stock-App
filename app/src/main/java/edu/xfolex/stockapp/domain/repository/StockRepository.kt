package edu.xfolex.stockapp.domain.repository

import edu.xfolex.stockapp.domain.model.CompanyInfo
import edu.xfolex.stockapp.domain.model.CompanyListing
import edu.xfolex.stockapp.domain.model.IntradayInfo
import edu.xfolex.stockapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query:String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>
}