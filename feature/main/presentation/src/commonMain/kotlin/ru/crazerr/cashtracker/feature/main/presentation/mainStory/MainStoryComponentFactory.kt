package ru.crazerr.cashtracker.feature.main.presentation.mainStory

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.core.utils.presentation.ComponentFactory
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponentFactory

interface MainStoryComponentFactory : ComponentFactory<MainStoryComponent, MainStoryComponentAction>

class MainStoryComponentFactoryImpl(
    private val mainComponentFactory: MainComponentFactory,
) : MainStoryComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (MainStoryComponentAction) -> Unit,
    ) = MainStoryComponent(
        componentContext = componentContext,
        factories = MainStoryComponentFactories(
            mainComponentFactory = mainComponentFactory
        ),
        onAction = onAction
    )
}
