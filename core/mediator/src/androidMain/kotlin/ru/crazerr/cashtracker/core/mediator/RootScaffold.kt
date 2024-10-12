package ru.crazerr.cashtracker.core.mediator

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal actual fun RootScaffold(
    modifier: Modifier,
    navigationBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = { navigationBar() },
        snackbarHost = {},
    ) { innerPadding ->
        content()
    }
}
