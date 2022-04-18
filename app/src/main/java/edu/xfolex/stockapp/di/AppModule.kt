package edu.xfolex.stockapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.xfolex.stockapp.data.local.database.StockDatabase
import edu.xfolex.stockapp.data.remote.api.StockMarketApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockMarketApi = Retrofit.Builder()
        .baseUrl(StockMarketApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()

    @Provides
    @Singleton
    fun providesStockDatabase(app: Application): StockDatabase = Room.databaseBuilder(
        app,
        StockDatabase::class.java,
        "stockdb.db"
    ).build()
}