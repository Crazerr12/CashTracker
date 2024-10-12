package ru.crazerr.cashtracker.core.utils.dateTime

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

private const val MIN_TIME = 0
private const val MAX_HOUR = 23
private const val MAX_MINUTES = 59
private const val MAX_SECONDS = 59
private const val MAX_NANOSECONDS = 999_999_999

fun LocalTime.Companion.now(): LocalTime {
    return LocalDateTime.now().time
}

fun LocalTime.Companion.min(): LocalTime {
    return LocalTime(MIN_TIME, MIN_TIME)
}

fun LocalTime.Companion.max(): LocalTime {
    return LocalTime(MAX_HOUR, MAX_MINUTES, MAX_SECONDS, MAX_NANOSECONDS)
}
