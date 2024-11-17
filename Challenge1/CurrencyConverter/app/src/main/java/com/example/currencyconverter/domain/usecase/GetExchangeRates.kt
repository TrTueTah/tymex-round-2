package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.model.Currency
import com.example.currencyconverter.domain.repository.CurrencyRepository

class GetExchangeRates(private val currencyRepository: CurrencyRepository) {
    suspend operator fun invoke(base: String, symbols: String): Map<String, Double> {
        return currencyRepository.getExchangeRates(base, symbols)
    }
}