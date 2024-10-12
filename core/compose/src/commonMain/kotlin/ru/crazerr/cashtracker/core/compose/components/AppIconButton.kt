package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import ru.crazerr.cashtracker.core.compose.theme.AppTheme

internal val IconSize = 24.dp

@Composable
expect fun AppIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    onClick: () -> Unit,
    contentDescription: String? = null,
    iconTint: Color = AppTheme.Colors.white,
    backgroundTint: Color = AppTheme.Colors.background,
)
