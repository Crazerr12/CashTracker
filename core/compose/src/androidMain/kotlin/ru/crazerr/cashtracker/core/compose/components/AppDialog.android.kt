package ru.crazerr.cashtracker.core.compose.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties

@Composable
actual fun AppDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    properties: DialogProperties,
    title: @Composable ColumnScope.() -> Unit,
    footer: @Composable ColumnScope.() -> Unit,
    content: @Composable ColumnScope.(Modifier) -> Unit,
) {
}
