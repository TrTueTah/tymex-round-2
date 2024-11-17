package com.example.currencyconverter.utils

import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.data.api.CurrencyApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val url = BuildConfig.BASE_URL
    private val client = OkHttpClient.Builder().build()

    val apiService: CurrencyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApiService::class.java)
    }
}