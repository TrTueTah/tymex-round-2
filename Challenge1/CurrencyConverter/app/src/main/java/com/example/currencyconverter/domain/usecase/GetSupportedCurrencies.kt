package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.model.Currency
import com.example.currencyconverter.domain.repository.CurrencyRepository

class GetSupportedCurrencies(private val currencyRepository: CurrencyRepository) {
    suspend operator fun invoke(): List<Currency> {
        return currencyRepository.getSupportedCurrencies()
    }
}