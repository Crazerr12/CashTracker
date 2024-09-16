package ru.crazerr.cashtracker.core.utils.dateTime

import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone


fun LocalTime.Companion.now(): LocalTime {
    val fds = LocalTime(14, 30)
    return LocalDateTime.now().time
}

fun LocalTime.Companion.min(): LocalTime {
    return LocalTime(0, 0)
}

fun LocalTime.Companion.max(): LocalTime {
    return LocalTime(23, 59, 59, 999999999)
}