package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.domain.model.Currency

interface  CurrencyRepository {
    suspend fun getSupportedCurrencies(): List<Currency>
    suspend fun getExchangeRates(base: String, symbols: String): Map<String, Double>
//    suspend fun convertCurrency(from: String, to: String, amount: Double): Double
}