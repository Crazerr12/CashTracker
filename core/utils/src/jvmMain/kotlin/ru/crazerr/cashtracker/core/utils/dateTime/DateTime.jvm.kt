package ru.crazerr.cashtracker.core.utils.dateTime

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.text.DateFormatSymbols
import java.time.format.DateTimeFormatter
import java.util.Locale

actual fun format(localDateTime: LocalDateTime): String {
    return DateTimeFormatter
        .ofPattern("dd/MM/yyyy - HH:mm", Locale.getDefault())
        .format(localDateTime.toJavaLocalDateTime())
}

actual fun getMonthNames(languageTag: String): List<String> {
    val locale = Locale.getDefault()
    val symbols = DateFormatSymbols.getInstance(locale)
    return symbols.months.take(12)
}