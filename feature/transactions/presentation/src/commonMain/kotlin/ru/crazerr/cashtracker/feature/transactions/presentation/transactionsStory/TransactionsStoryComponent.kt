package ru.crazerr.cashtracker.feature.transactions.presentation.transactionsStory

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsComponent
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsComponentAction

class TransactionsStoryComponent(
    componentContext: ComponentContext,
    private val factories: TransactionsStoryComponentFactories,
    private val onAction: (TransactionsStoryComponentAction) -> Unit,
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    val stack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Transactions,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            Config.Transactions -> createTransactionsChild(componentContext = componentContext)
        }

    private fun createTransactionsChild(componentContext: ComponentContext) =
        Child.TransactionsChild(
            component = factories.transactionsComponentFactory.create(
                componentContext = componentContext,
                onAction = { action ->
                    when (action) {
                        TransactionsComponentAction.BackClick -> TODO()
                    }
                }
            )
        )

    @Serializable
    sealed interface Config {
        data object Transactions : Config
    }

    sealed interface Child {
        data class TransactionsChild(val component: TransactionsComponent) : Child
    }
}