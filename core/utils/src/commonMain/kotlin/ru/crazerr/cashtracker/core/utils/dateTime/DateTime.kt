package ru.crazerr.cashtracker.core.utils.dateTime

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

internal const val NUMBER_OF_MONTH = 12
internal const val DATE_START_PADDING = 2
internal const val YEAR_PART = 0
internal const val MONTH_PART = 1
internal const val DAY_PART = 2

fun LocalDateTime.Companion.now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}

fun LocalDateTime.plus(value: Long, unit: DateTimeUnit.TimeBased): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    return this.toInstant(timeZone)
        .plus(value, unit)
        .toLocalDateTime(timeZone)
}

fun LocalDateTime.minus(value: Long, unit: DateTimeUnit.TimeBased): LocalDateTime {
    val timeZone = TimeZone.currentSystemDefault()
    return this.toInstant(timeZone)
        .minus(value, unit)
        .toLocalDateTime(timeZone)
}

expect fun format(localDateTime: LocalDateTime): String

expect fun getMonthNames(languageTag: String): List<String>

expect fun String.fromInput(): LocalDate

expect fun Long.toLocalDate(): LocalDate

expect fun LocalDate.toEpochMilliSeconds(): Long

expect fun LocalDate.toInput(): String
