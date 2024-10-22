package ru.crazerr.cashtracker.core.utils.dateTime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

private const val FIRST_DAY_OF_MONTH = 1

fun LocalDate.Companion.now(): LocalDate {
    return LocalDateTime.now().date
}

fun LocalDate.atStartOfDay(): LocalDateTime {
    return LocalDateTime(this, LocalTime.min())
}

fun LocalDate.atEndOfDay(): LocalDateTime {
    return LocalDateTime(this, LocalTime.max())
}

fun LocalDate.atStartOfMonth(): LocalDate {
    return LocalDate(year = year, month = month, dayOfMonth = FIRST_DAY_OF_MONTH)
}

fun LocalDate.atCurrentDayOfMonth(): LocalDate {
    return LocalDate(year = year, month = month, dayOfMonth = dayOfMonth)
}
