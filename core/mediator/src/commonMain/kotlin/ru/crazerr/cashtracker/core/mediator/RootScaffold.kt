package ru.crazerr.cashtracker.core.mediator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal expect fun RootScaffold(
    modifier: Modifier = Modifier,
    navigationBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
)