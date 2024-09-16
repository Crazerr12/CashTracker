package ru.crazerr.cashtracker.core.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

object AppTheme {
    val Dimens: AppDimens
        @Composable
        get() = LocalDimens.current

    val Colors: AppColors
        @Composable
        get() = LocalColors.current

    val TextStyles: AppTextStyles
        @Composable
        get() = LocalTextStyles.current
}

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }

    val colorsScheme = when {
        isDarkState.value -> darkColors
        else -> defaultColors
    }

    val dimens = defaultDimens
    val textStyles = defaultTextStyles

    CompositionWrapper(
        dimens = dimens,
        colors = colorsScheme,
        textStyles = textStyles,
        isDarkState = isDarkState,
        content = {
            SystemAppearance(!isDarkState.value)
            content()
        }
    )
}

@Composable
private fun CompositionWrapper(
    dimens: AppDimens,
    colors: AppColors,
    textStyles: AppTextStyles,
    isDarkState: MutableState<Boolean>,
    content: @Composable () -> Unit,
) {
    val dimensCalculation = remember { dimens }
    val colorCalculation = remember { colors }
    val textStylesCalculation = remember { textStyles }

    CompositionLocalProvider(
        LocalDimens provides dimensCalculation,
        LocalTextStyles provides textStylesCalculation,
        LocalColors provides colorCalculation,
        LocalThemeIsDark provides isDarkState,
        content = content
    )
}

/**
 * Установка цвета системных баров.
 */
@Composable
internal expect fun SystemAppearance(isDark: Boolean)

private val LocalDimens = staticCompositionLocalOf {
    defaultDimens
}

private val LocalColors = staticCompositionLocalOf {
    defaultColors
}

private val LocalTextStyles = staticCompositionLocalOf {
    defaultTextStyles
}

private val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }