package com.example.currencyconverter.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.domain.model.Currency
import com.example.currencyconverter.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                CurrencyConverterUI(viewModel)
            }
        }
    }
}

@Composable
fun CurrencyConverterUI(viewModel: MainViewModel ) {
    val inputAmount by viewModel.inputAmount.collectAsState()
    val inputCurrency by viewModel.inputCurrency.collectAsState()
    val outputAmount by viewModel.outputAmount.collectAsState()
    val outputCurrency by viewModel.outputCurrency.collectAsState()
    val currencies by viewModel.currencies.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    Log.d("MainActivity", "Currencies: $currencies")

    if (currencies.isEmpty()) {
        Text(text = "Loading...", color = Color.Gray)
    } else {
        if (errorMessage != null) {
            AlertDialog(
                onDismissRequest = { viewModel.clearErrorMessage() },
                title = { Text("Error") },
                text = { Text(errorMessage!!) },
                confirmButton = {
                    Button(onClick = { viewModel.clearErrorMessage() }) {
                        Text("OK")
                    }
                }
            )
        }
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CurrencyInput(
                    label = "Amount",
                    amount = inputAmount,
                    onAmountChange = { viewModel.updateInputAmount(it) },
                    currencyCode = inputCurrency,
                    onCurrencyChange = { viewModel.updateInputCurrency(it) },
                    currencies = currencies
                )
                CurrencyInput(
                    label = "Converted to",
                    amount = outputAmount,
                    onAmountChange = { },
                    currencyCode = outputCurrency,
                    onCurrencyChange = { viewModel.updateOutputCurrency(it) },
                    readOnly = true,
                    currencies = currencies
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyInput(
    label: String = "Input",
    amount: String,
    onAmountChange: (String) -> Unit,
    currencyCode: String,
    onCurrencyChange: (String) -> Unit,
    readOnly: Boolean = false,
    currencies: List<Currency>
) {
    var expanded by remember { mutableStateOf(false) }

    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = label,
        )
        Column {
            OutlinedTextField(
                value = amount,
                onValueChange = onAmountChange,
                readOnly = readOnly,
                placeholder = { Text("0.00") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                trailingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { expanded = true }
                    ) {
                        Text(
                            text = currencyCode,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                    DropdownMenu (
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.height(250.dp)
                    ) {
                        currencies.forEach { currency ->
                            DropdownMenuItem(
                                text = { Text(currency.code) },
                                onClick = {
                                    onCurrencyChange(currency.code)
                                    expanded = false
                                }
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

        }
    }
}
