package com.example.currencyconverter.data.api

import com.example.currencyconverter.data.model.ExchangeRateResponse
import com.example.currencyconverter.data.model.SymbolResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("symbols")
    suspend fun getSupportedCurrencies(@Query("access_key") apiKey: String): SymbolResponse
    // Can not use this API: Access Restricted - Your current Subscription Plan does not support this API Function.
//    @GET("convert")
//    suspend fun convertCurrency(
//        @Query("access_key") apiKey: String,
//        @Query("from") from: String,
//        @Query("to") to: String,
//        @Query("amount") amount: Double
//    ): ConvertResponse
    @GET("latest")
    suspend fun getExchangeRates(
        @Query("access_key") apiKey: String,
        @Query("base") from: String,
        @Query("symbols") to: String): ExchangeRateResponse
}