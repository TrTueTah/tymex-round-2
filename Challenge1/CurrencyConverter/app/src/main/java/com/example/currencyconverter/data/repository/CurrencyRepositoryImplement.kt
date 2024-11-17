package com.example.currencyconverter.data.repository

import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.data.api.CurrencyApiService
import com.example.currencyconverter.domain.model.Currency
import com.example.currencyconverter.domain.repository.CurrencyRepository

class CurrencyRepositoryImplement(private val apiService: CurrencyApiService) : CurrencyRepository {

    override suspend fun getSupportedCurrencies(): List<Currency> {
        val response = apiService.getSupportedCurrencies(BuildConfig.API_KEY)
        return response.symbols.map { Currency(it.key, it.value, it.value) }
    }

    // Convert the currency from one to another
//    override suspend fun convertCurrency(
//        from: String,
//        to: String,
//        amount: Double
//    ): Double {
//        val response = apiService.convertCurrency(
//            apiKey = BuildConfig.API_KEY,
//            from = from,
//            to = to,
//            amount = amount
//        )
//        return response.result
//    }
    override suspend fun getExchangeRates(base: String, symbols: String): Map<String, Double> {
        val response = apiService.getExchangeRates(BuildConfig.API_KEY, base, symbols)
        return response.rates
    }
}