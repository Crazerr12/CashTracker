package ru.crazerr.cashtracker.feature.transactions.presentation.transactionsStory

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.core.utils.presentation.ComponentFactory
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsComponentFactory

interface TransactionsStoryComponentFactory :
    ComponentFactory<TransactionsStoryComponent, TransactionsStoryComponentAction>

internal class TransactionsStoryComponentFactoryImpl(
    private val transactionsComponentFactory: TransactionsComponentFactory,
) : TransactionsStoryComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (TransactionsStoryComponentAction) -> Unit,
    ) = TransactionsStoryComponent(
        componentContext = componentContext,
        factories = TransactionsStoryComponentFactories(
            transactionsComponentFactory = transactionsComponentFactory
        ),
        onAction = onAction
    )
}
