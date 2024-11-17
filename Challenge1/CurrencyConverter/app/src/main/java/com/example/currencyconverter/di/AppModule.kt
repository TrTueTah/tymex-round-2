package com.example.currencyconverter.di

import com.example.currencyconverter.data.repository.CurrencyRepositoryImplement
import com.example.currencyconverter.domain.repository.CurrencyRepository
import com.example.currencyconverter.domain.usecase.GetExchangeRates
import com.example.currencyconverter.domain.usecase.GetSupportedCurrencies
import com.example.currencyconverter.ui.viewmodel.MainViewModel
import com.example.currencyconverter.utils.RetrofitInstance
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Retrofit API service
    single { RetrofitInstance.apiService }

    // Repository instance
    single<CurrencyRepository> { CurrencyRepositoryImplement(get()) }

    // Use cases
    factory { GetSupportedCurrencies(get()) }
    factory { GetExchangeRates(get()) }
//    factory { ConvertCurrency(get()) }

    // ViewModel
    viewModel { MainViewModel(get(), get()) }
}