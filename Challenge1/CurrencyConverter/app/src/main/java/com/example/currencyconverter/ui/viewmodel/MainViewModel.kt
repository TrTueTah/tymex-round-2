package com.example.currencyconverter.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.model.Currency
import com.example.currencyconverter.domain.usecase.GetExchangeRates
import com.example.currencyconverter.domain.usecase.GetSupportedCurrencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getSupportedCurrencies: GetSupportedCurrencies,
    private val getExchangeRates: GetExchangeRates
) : ViewModel() {

    // State for input amount (user input)
    private val _inputAmount = MutableStateFlow("")
    val inputAmount: StateFlow<String> = _inputAmount

    // State for the selected input currency (base currency)
    private val _inputCurrency = MutableStateFlow("EUR")
    val inputCurrency: StateFlow<String> = _inputCurrency

    // State for the converted output amount (result)
    private val _outputAmount = MutableStateFlow("")
    val outputAmount: StateFlow<String> = _outputAmount

    // State for the selected output currency (target currency)
    private val _outputCurrency = MutableStateFlow("VND")
    val outputCurrency: StateFlow<String> = _outputCurrency

    // List of supported currencies loaded from the API
    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies: StateFlow<List<Currency>> = _currencies

    // State for error messages
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Initialization block to load supported currencies when ViewModel is created
    init {
        loadSupportedCurrencies()
    }

    // Clears the current error message
    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    // Load supported currencies from the use case
    private fun loadSupportedCurrencies() {
        viewModelScope.launch {
            try {
                _currencies.value = getSupportedCurrencies()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load currencies"
            }
        }
    }

    // Update input amount
    fun updateInputAmount(amount: String) {
        _inputAmount.value = amount
        convertCurrency()
    }

    // Update input currency
    fun updateInputCurrency(currency: String) {
        if(currency != "EUR") {
            _inputCurrency.value = "EUR"
            _errorMessage.value = "Please select EUR as the base currency, other currency can not select as base currency due to the restriction of API."
        } else {
            _inputCurrency.value = currency
        }
        convertCurrency()
    }

    // Update output currency
    fun updateOutputCurrency(currency: String) {
        _outputCurrency.value = currency
        convertCurrency()
    }



    // Perform the currency conversion
    private fun convertCurrency() {
        // Validate input amount
        val amount = _inputAmount.value.toDoubleOrNull()
        if (amount == null) {
            _errorMessage.value = "Invalid input. Please enter a valid number."
            return
        }

        if (amount <= 0) {
            _errorMessage.value = "Amount must be greater than zero."
            return
        }

        val fromCurrency = _inputCurrency.value
        val toCurrency = _outputCurrency.value

        viewModelScope.launch {
            try {
                // Fetch exchange rates
                val exchangeRates = getExchangeRates(fromCurrency, toCurrency)

                val targetRate = exchangeRates[toCurrency] ?: throw Exception("Rate not found for $toCurrency")
                if(targetRate == null) {
                    _errorMessage.value = "Exchange rate not found for the selected currencies."
                    Log.e("CurrencyConverter", "Rate not found for: From: $fromCurrency, To: $toCurrency")
                    return@launch
                }
                // Calculate the converted amount
                val convertedAmount = amount * targetRate

                // Update the output amount state
                _outputAmount.value = String.format("%.2f", convertedAmount)
            } catch (e: java.net.UnknownHostException) {
                // Handle network failure
                _errorMessage.value = "Network error. Please check your internet connection."
                Log.e("CurrencyConverter", "Network error: ${e.message}")
            } catch (e: java.net.SocketTimeoutException) {
                // Handle timeout error
                _errorMessage.value = "Request timed out. Please try again later."
                Log.e("CurrencyConverter", "Timeout error: ${e.message}")
            }
            catch (e: Exception) {
                // Handle any other unexpected exceptions
                _errorMessage.value = "Failed to convert currency due to an unexpected error. Message:[${e.message}]"
                Log.e("CurrencyConverter", "Unexpected error: ${e.message}")
            }
        }
    }
}