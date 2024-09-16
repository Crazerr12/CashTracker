package ru.crazerr.cashtracker.feature.main.presentation.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponent

@Composable
actual fun MainView(component: MainComponent) {
    val state by component.state.subscribeAsState()
}