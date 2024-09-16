package ru.crazerr.cashtracker.core.utils.dateTime

import android.annotation.SuppressLint
import android.os.Build
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import org.intellij.lang.annotations.Pattern
import java.text.DateFormatSymbols
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("NewApi")
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