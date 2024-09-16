package ru.crazerr.cashtracker.core.compose.theme

import androidx.compose.ui.graphics.Color

data class AppColors(
    val background: Color,
    val white: Color,
    val black: Color,
    val blue: Color,
    val green: Color,
    val yellow: Color,
    val red: Color,
)

internal val defaultColors = AppColors(
    background = Color(0xFFF8F9FA),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    blue = Color(0xFF407BF7),
    green = Color(0xFF22C761),
    yellow = Color(0xFFE8B526),
    red = Color(0xFFF04241),
)

internal val darkColors = AppColors(
    background = Color(0xFF1A2530),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    blue = Color(0xFF407BF7),
    green = Color(0xFF22C761),
    yellow = Color(0xFFE8B526),
    red = Color(0xFFF04241),
)