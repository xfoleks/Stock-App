package edu.xfolex.stockapp.data.remote.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockMarketApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    companion object {
        const val API_KEY = "P3OU61S7X0FIPJ9Z"
        const val BASE_URL = "http://alphavantage.co"
    }
}