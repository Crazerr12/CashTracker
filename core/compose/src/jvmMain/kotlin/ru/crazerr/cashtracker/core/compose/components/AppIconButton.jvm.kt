package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme

@Composable
actual fun AppIconButton(
    modifier: Modifier,
    icon: Painter,
    onClick: () -> Unit,
    contentDescription: String?,
    iconTint: Color,
    backgroundTint: Color,
) {
    Box(
        modifier = modifier
            .background(color = backgroundTint, shape = CircleShape)
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .padding(AppTheme.Dimens.dimen4)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = icon,
            contentDescription = contentDescription,
            tint = iconTint
        )
    }
}

@Preview
@Composable
private fun AppIconButtonArrowForwardPreview() {
    AppIconButton(
        modifier = Modifier,
        icon = AppIcons.ArrowForward.painter,
        contentDescription = AppIcons.ArrowForward.contentDescription,
        onClick = {},
        iconTint = AppTheme.Colors.white,
        backgroundTint = AppTheme.Colors.background,
    )
}

@Preview
@Composable
private fun AppIconButtonArrowBackPreview() {
    AppIconButton(
        modifier = Modifier,
        icon = AppIcons.ArrowBack.painter,
        contentDescription = AppIcons.ArrowBack.contentDescription,
        onClick = {},
        iconTint = AppTheme.Colors.white,
        backgroundTint = AppTheme.Colors.background,
    )
}
