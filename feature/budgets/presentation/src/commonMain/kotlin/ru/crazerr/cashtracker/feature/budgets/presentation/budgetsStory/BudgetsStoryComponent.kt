package ru.crazerr.cashtracker.feature.budgets.presentation.budgetsStory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsComponent

class BudgetsStoryComponent(
    componentContext: ComponentContext,
    private val onAction: (BudgetsStoryComponentAction) -> Unit,
    private val factories: BudgetsStoryComponentFactories,
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    val stack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Budgets,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            Config.Budgets -> createBudgetsChild(componentContext = componentContext)
        }

    private fun createBudgetsChild(componentContext: ComponentContext) =
        Child.Budgets(
            component = factories.budgetsComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        else -> {}
                    }
                }
            )
        )

    @Serializable
    sealed interface Config {
        data object Budgets : Config
    }

    sealed interface Child {
        data class Budgets(val component: BudgetsComponent) : Child
    }
}
