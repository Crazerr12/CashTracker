package ru.crazerr.cashtracker.core.mediator

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.crazerr.cashtracker.feature.main.presentation.mainStory.ui.MainCoordinator
import ru.crazerr.cashtracker.feature.transactions.presentation.transactionsStory.ui.TransactionsCoordinator

@OptIn(ExperimentalCoilApi::class,)
@Composable
fun RootCoordinator(
    rootComponent: RootComponent,
    modifier: Modifier = Modifier,
) {
    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context = context)
    }

    RootNavigationDrawer(rootComponent = rootComponent) {
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

                is RootComponent.Child.TransactionsChild -> TransactionsCoordinator(component = child.component)

                is RootComponent.Child.BudgetChild -> TODO()
                is RootComponent.Child.SettingsChild -> TODO()
                is RootComponent.Child.AccountsChild -> TODO()
                is RootComponent.Child.GoalsChild -> TODO()
            }
        }
    }
}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()
