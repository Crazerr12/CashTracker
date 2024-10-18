package ru.crazerr.cashtracker.core.utils.formatter

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class CurrencyFormatter {
    private val decimalFormat: DecimalFormat by lazy {
        val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
            decimalSeparator = '.'
            groupingSeparator = ' '
        }

        DecimalFormat("###.##", symbols)
    }

    fun format(value: Float) = decimalFormat.format(value)
}
