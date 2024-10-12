package ru.crazerr.cashtracker.core.utils.dateTime

import android.annotation.SuppressLint
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.text.DateFormatSymbols
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("NewApi")
actual fun format(localDateTime: LocalDateTime): String {
    return DateTimeFormatter
        .ofPattern("dd/MM/yyyy - HH:mm", Locale.getDefault())
        .format(localDateTime.toJavaLocalDateTime())
}

actual fun getMonthNames(languageTag: String): List<String> {
    val locale = Locale.getDefault()
    val symbols = DateFormatSymbols.getInstance(locale)
    return symbols.months.take(NUMBER_OF_MONTH)
}

actual fun String.fromInput(): LocalDate {
    TODO("Not yet implemented")
}

actual fun Long.toLocalDate(): LocalDate {
    TODO("Not yet implemented")
}

actual fun LocalDate.toInput(): String {
    TODO("Not yet implemented")
}

actual fun LocalDate.toEpochMilliSeconds(): Long {
    TODO("Not yet implemented")
}
