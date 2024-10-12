package ru.crazerr.cashtracker.core.utils

actual fun String.toDateFormat(): String {
    val digits = this.filter { it.isDigit() }
    val length = digits.length

    return when {
        length <= MONTH_START_INDEX -> digits
        length <= YEAR_START_INDEX -> "${digits.substring(DAY_START_INDEX, MONTH_START_INDEX)}.${
            digits.substring(
                MONTH_START_INDEX
            )
        }"

        length <= YEAR_END_INDEX -> "${digits.substring(DAY_START_INDEX, MONTH_START_INDEX)}.${
            digits.substring(
                MONTH_START_INDEX,
                YEAR_START_INDEX
            )
        }.${digits.substring(YEAR_START_INDEX)}"

        else -> "${digits.substring(DAY_START_INDEX, MONTH_START_INDEX)}.${
            digits.substring(
                MONTH_START_INDEX,
                YEAR_START_INDEX
            )
        }.${digits.substring(YEAR_START_INDEX, YEAR_END_INDEX)}"
    }
}
