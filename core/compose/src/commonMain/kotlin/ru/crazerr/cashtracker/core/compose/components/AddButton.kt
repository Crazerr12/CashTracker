package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme

@Composable
expect fun AddButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(AppTheme.Dimens.dimen10),
    tint: Color = AppTheme.Colors.white,
    painter: Painter = AppIcons.PlusIcon.painter,
    background: Color = AppTheme.Colors.background,
    contentDescription: String? = null,
)
