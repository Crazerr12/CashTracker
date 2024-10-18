package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.crazerr.cashtracker.core.compose.theme.AppTheme

@Composable
actual fun AppDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    properties: DialogProperties,
    title: @Composable ColumnScope.() -> Unit,
    footer: @Composable ColumnScope.() -> Unit,
    content: @Composable ColumnScope.(Modifier) -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Column(
            modifier = modifier
                .heightIn(max = 700.dp)
                .clip(shape = RoundedCornerShape(AppTheme.Dimens.dimen20))
                .background(
                    color = AppTheme.Colors.background,
                    shape = RoundedCornerShape(AppTheme.Dimens.dimen20)
                )
                .padding(AppTheme.Dimens.dimen20),
        ) {
            title()

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

            content(Modifier.weight(1f, fill = false))

            Spacer(modifier = Modifier.height(AppTheme.Dimens.dimen12))

            footer()
        }
    }
}
