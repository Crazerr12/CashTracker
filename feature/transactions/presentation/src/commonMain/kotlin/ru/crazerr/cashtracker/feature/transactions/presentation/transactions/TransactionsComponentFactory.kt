package ru.crazerr.cashtracker.feature.transactions.presentation.transactions

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.core.utils.presentation.ComponentFactory

interface TransactionsComponentFactory :
    ComponentFactory<TransactionsComponent, TransactionsComponentAction>

internal class TransactionsComponentFactoryImpl() : TransactionsComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (TransactionsComponentAction) -> Unit,
    ) = TransactionsComponent(
        componentContext = componentContext,
        onAction = onAction
    )
}
