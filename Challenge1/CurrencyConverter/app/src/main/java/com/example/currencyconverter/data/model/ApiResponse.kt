package com.example.currencyconverter.data.model

import android.icu.text.IDNA.Info
import retrofit2.http.Query

data class SymbolResponse(
    val success: Boolean,
    val symbols: Map<String, String>
)

data class ExchangeRateResponse(
    val success: Boolean,
    val rates: Map<String, Double>
)
//data class ConvertResponse(
//    val success: Boolean,
//    val query: Query,
//    val info: Info,
//    val date: String,
//    val result: Double
//)