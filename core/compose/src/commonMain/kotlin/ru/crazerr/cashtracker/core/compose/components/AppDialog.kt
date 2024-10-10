package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import cashtracker.core.compose.generated.resources.Res
import cashtracker.core.compose.generated.resources.app_dialog_button_cancel_text
import cashtracker.core.compose.generated.resources.app_dialog_button_save_text
import org.jetbrains.compose.resources.stringResource
import ru.crazerr.cashtracker.core.compose.theme.AppTheme

@Composable
expect fun AppDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    title: @Composable ColumnScope.() -> Unit,
    footer: @Composable ColumnScope.() -> Unit,
    content: @Composable ColumnScope.(Modifier) -> Unit,
)

@Composable
fun ButtonsFooter(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.End) {
        AppButton(
            text = stringResource(Res.string.app_dialog_button_cancel_text),
            onClick = onCancelClick
        )
        Spacer(modifier = Modifier.width(AppTheme.Dimens.dimen8))
        AppButton(
            text = stringResource(Res.string.app_dialog_button_save_text),
            onClick = onSaveClick
        )
    }
}
