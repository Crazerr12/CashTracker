package ru.crazerr.cashtracker.core.utils

actual fun String.toDateFormat(): String {
    val digits = this.filter { it.isDigit() }
    val length = digits.length

    return when {
        length <= 2 -> digits
        length <= 4 -> "${digits.substring(0, 2)}.${digits.substring(2)}"
        length <= 8 -> "${digits.substring(0, 2)}.${digits.substring(2, 4)}.${digits.substring(4)}"
        else -> "${digits.substring(0, 2)}.${digits.substring(2, 4)}.${digits.substring(4, 8)}"
    }
}