package ru.crazerr.cashtracker.core.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import cashtracker.core.compose.generated.resources.Res
import cashtracker.core.compose.generated.resources.roboto
import cashtracker.core.compose.generated.resources.roboto_italic

data object AppTextStyles {
    private val roboto: FontFamily
        @Composable
        get() = FontFamily(
            Font(Res.font.roboto),
            Font(Res.font.roboto_italic)
        )

    val headlineTitle: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Black,
            fontSize = 30.sp,
            lineHeight = 38.sp,
        )

    val title: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
        )

    val subtitle: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp,
            lineHeight = 26.sp,
        )

    val body: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 22.sp,
        )

    val caption: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        )

    val button: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 1.25.sp,
        )

    val overline: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.W400,
            fontSize = 10.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.5.sp
        )

    val input: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 24.sp,
        )
}

internal val defaultTextStyles = AppTextStyles