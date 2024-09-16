package ru.crazerr.cashtracker.feature.main.presentation.mainStory.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.crazerr.cashtracker.feature.main.presentation.main.ui.MainView
import ru.crazerr.cashtracker.feature.main.presentation.mainStory.MainStoryComponent

@Composable
fun MainCoordinator(
    component: MainStoryComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.stack,
        modifier = modifier,
    ) {
        when (val child = it.instance) {
            is MainStoryComponent.Child.MainChild -> MainView(component = child.component)
        }
    }
}