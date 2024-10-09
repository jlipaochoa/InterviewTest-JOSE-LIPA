package com.example.interviewtest.case1

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Case1Screen() {
    var currencyAmount by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableDoubleStateOf(0.0)
    }
    var currency by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Currency converter")
        CurrencySelector(onSelect = {
            currency = it
        })
        TextField(value = "${currencyAmount}", onValueChange = {
            if (it.isEmpty()) {
                currencyAmount = ""
                return@TextField
            }
            if (it.isDigitsOnly()) {
                currencyAmount = it
                amount = currencyAmount.toDouble()
            }
        })

        Text("Currency converted: ${amount.convertAmount(currency).toCurrencyFormat()}")
    }
}

@Composable
fun CurrencySelector(onSelect: (String) -> Unit) {
    var selectedOption by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption == "USD",
                onClick = {
                    selectedOption = "USD"
                    onSelect.invoke(selectedOption)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "USD")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption == "EUR",
                onClick = {
                    selectedOption = "EUR"
                    onSelect.invoke(selectedOption)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "EUR")
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Opción seleccionada: $selectedOption")
    }
}

fun Double.toCurrencyFormat(): String {
    val decimalFormatter = DecimalFormat("#00.00")
    return decimalFormatter.format(this)
}

fun Double.convertAmount(currency: String): Double {
    val conversion = if (currency == "USD") 3.73 else 4.09
    return this * conversion
}