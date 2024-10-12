package ru.crazerr.cashtracker.core.utils.dateTime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

fun LocalDate.Companion.now(): LocalDate {
    return LocalDateTime.now().date
}

fun LocalDate.atStartOfDay(): LocalDateTime {
    return LocalDateTime(this, LocalTime.min())
}

fun LocalDate.atEndOfDay(): LocalDateTime {
    return LocalDateTime(this, LocalTime.max())
}
