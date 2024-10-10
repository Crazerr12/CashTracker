package ru.crazerr.cashtracker.core.utils.dateTime

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
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

actual fun String.fromInput(): LocalDate {
    val parts = this.split(".")
    val formattedDate = "${parts[2]}-${parts[1]}-${parts[0]}"
    return LocalDate.parse(formattedDate)
}

actual fun Long.toLocalDate(): LocalDate {
    val instant = Instant.fromEpochMilliseconds(this)
    return instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
}

actual fun LocalDate.toInput(): String {
    val day = this.dayOfMonth.toString().padStart(2, '0')
    val month = this.monthNumber.toString().padStart(2, '0')
    val year = this.year

    return "$day.$month.$year"
}

actual fun LocalDate.toEpochMilliSeconds(): Long {
    val instant = this.atStartOfDay().toInstant(TimeZone.currentSystemDefault())

    return instant.toEpochMilliseconds()
}