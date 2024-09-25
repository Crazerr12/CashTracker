package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.compose.utils.conditional

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    loading: Boolean = false,
    contentColor: Color = AppTheme.Colors.white,
    backgroundColor: Color = AppTheme.Colors.blue,
    shape: Shape = RoundedCornerShape(AppTheme.Dimens.dimen16),
) {
    val textColor = if (loading) backgroundColor else contentColor
    Box(
        modifier = modifier
            .clip(shape = shape)
            .background(color = backgroundColor, shape = shape)
            .conditional(
                condition = !loading,
                ifTrue = {
                    clickable(
                        enabled = enabled,
                        onClick = onClick
                    )
                }
            )
            .padding(horizontal = AppTheme.Dimens.dimen8, vertical = AppTheme.Dimens.dimen4),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier) {
            BasicText(
                text = text,
                style = AppTheme.TextStyles.button.copy(color = textColor)
            )
        }

        if (loading) {
            CircularProgressIndicator(
                color = contentColor,
            )
        }
    }
}