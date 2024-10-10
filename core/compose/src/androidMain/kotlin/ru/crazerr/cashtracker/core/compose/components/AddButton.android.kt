package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import ru.crazerr.cashtracker.core.compose.icons.AppIcons
import ru.crazerr.cashtracker.core.compose.theme.AppTheme

@Suppress("LongParameterList")
@Composable
actual fun AddButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
    shape: Shape,
    tint: Color,
    painter: Painter,
    background: Color,
    contentDescription: String?,
) {
    Row(
        modifier = modifier
            .clip(shape = shape)
            .background(
                color = background,
                shape = shape
            )
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .padding(all = AppTheme.Dimens.dimen10)
    ) {
        Icon(
            painter = painter,
            tint = tint,
            contentDescription = contentDescription,
        )

        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))

        Text(
            text = text,
            style = AppTheme.TextStyles.body.copy(color = tint),
        )
    }
}

@Preview
@Composable
private fun AddButtonWithTextPreview() {
    AddButton(
        modifier = Modifier,
        text = "text",
        onClick = { },
        enabled = true,
        shape = RoundedCornerShape(AppTheme.Dimens.dimen10),
        tint = AppTheme.Colors.white,
        painter = AppIcons.PlusIcon.painter,
        background = AppTheme.Colors.background,
        contentDescription = AppIcons.PlusIcon.contentDescription
    )
}

@Composable
@Preview
private fun AddButtonWithoutTextPreview() {
    AddButton(
        modifier = Modifier,
        text = "text",
        onClick = { },
        enabled = true,
        shape = RoundedCornerShape(AppTheme.Dimens.dimen10),
        tint = AppTheme.Colors.white,
        painter = AppIcons.PlusIcon.painter,
        background = AppTheme.Colors.background,
        contentDescription = AppIcons.PlusIcon.contentDescription
    )
}
