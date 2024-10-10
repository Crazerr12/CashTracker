package ru.crazerr.cashtracker.core.utils.dateTime

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

fun LocalTime.Companion.now(): LocalTime {
    return LocalDateTime.now().time
}

fun LocalTime.Companion.min(): LocalTime {
    return LocalTime(0, 0)
}

fun LocalTime.Companion.max(): LocalTime {
    return LocalTime(23, 59, 59, 999999999)
}
