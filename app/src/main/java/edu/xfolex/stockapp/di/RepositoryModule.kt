package edu.xfolex.stockapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.xfolex.stockapp.data.csv.CSVParser
import edu.xfolex.stockapp.data.csv.CompanyListingsParser
import edu.xfolex.stockapp.data.csv.IntradayInfoParser
import edu.xfolex.stockapp.data.repository.StockRepositoryImpl
import edu.xfolex.stockapp.domain.model.CompanyListing
import edu.xfolex.stockapp.domain.model.IntradayInfo
import edu.xfolex.stockapp.domain.repository.StockRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}