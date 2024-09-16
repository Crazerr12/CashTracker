package ru.crazerr.cashtracker.core.mediator

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.crazerr.cashtracker.feature.main.presentation.mainStory.ui.MainCoordinator

@Composable
fun RootCoordinator(
    rootComponent: RootComponent,
    modifier: Modifier = Modifier,
) {
    RootScaffold(
        modifier = modifier,
        navigationBar = {
            RootNavigationDrawer(rootComponent = rootComponent)
        }
    ) {
        RootCoordinatorContent(rootComponent = rootComponent)
    }
}

@Composable
private fun RootCoordinatorContent(
    rootComponent: RootComponent,
) {
    Box {
        Children(
            stack = rootComponent.stack
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.MainChild ->
                    MainCoordinator(component = child.component)

                is RootComponent.Child.BudgetChild -> TODO()
                is RootComponent.Child.SettingsChild -> TODO()
                is RootComponent.Child.TransactionChild -> TODO()
            }
        }
    }
}