package ru.crazerr.cashtracker.feature.main.presentation.mainStory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponent

class MainStoryComponent(
    componentContext: ComponentContext,
    private val factories: MainStoryComponentFactories,
    private val onAction: (MainStoryComponentAction) -> Unit,
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    val stack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Main,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            Config.Main -> createMainChild(componentContext = componentContext)
        }

    private fun createMainChild(componentContext: ComponentContext) =
        Child.MainChild(
            component = factories.mainComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                    }
                }
            )
        )

    @Serializable
    sealed interface Config {
        data object Main : Config
    }

    sealed interface Child {
        data class MainChild(val component: MainComponent) : Child
    }
}
