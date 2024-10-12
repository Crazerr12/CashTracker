package ru.crazerr.cashtracker.core.utils

internal const val DAY_START_INDEX = 0
internal const val MONTH_START_INDEX = 2
internal const val YEAR_START_INDEX = 4
internal const val YEAR_END_INDEX = 8

expect fun String.toDateFormat(): String
