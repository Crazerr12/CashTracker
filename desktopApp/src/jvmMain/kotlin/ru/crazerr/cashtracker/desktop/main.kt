package ru.crazerr.cashtracker.desktop

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import ru.crazerr.cashtracker.core.compose.theme.AppTheme
import ru.crazerr.cashtracker.core.mediator.RootCoordinator
import ru.crazerr.cashtracker.umbrella.Koin
import java.awt.Dimension

fun main() {
    Koin.setupKoin()

    val lifecycle = LifecycleRegistry()

    val rootComponent = runOnUiThread {
        DesktopRootComponent().create(lifecycle)
    }

    application {
        val windowState = rememberWindowState(width = 800.dp, height = 600.dp)

        LifecycleController(lifecycle, windowState)

        Window(
            title = "CashTracker",
            state = windowState,
            onCloseRequest = ::exitApplication,
        ) {
            window.minimumSize = Dimension(DefaultMinWidth, DefaultMinHeight)
            AppTheme {
                RootCoordinator(rootComponent = rootComponent)
            }
        }
    }
}

private const val DefaultMinWidth = 800
private const val DefaultMinHeight = 600
