package ru.crazerr.cashtracker.feature.transactions.presentation.transactions

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.core.utils.presentation.StateHolder
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.TransactionHeader

internal typealias TransactionsStateHolder = StateHolder<TransactionsState, TransactionsViewAction>

class TransactionsComponent(
    componentContext: ComponentContext,
    private val onAction: (TransactionsComponentAction) -> Unit,
) : TransactionsStateHolder(InitialTransactionsState), ComponentContext by componentContext {
    override fun obtainViewAction(action: TransactionsViewAction) {
        when (action) {
            TransactionsViewAction.CreateNewTransactionClick -> TODO()
            TransactionsViewAction.ManageTransactionsDate -> TODO()
            is TransactionsViewAction.SearchChange -> TODO()
            is TransactionsViewAction.SelectTab -> TODO()
            is TransactionsViewAction.SetAccountFilter -> TODO()
            is TransactionsViewAction.SetCategoryFilter -> TODO()
            is TransactionsViewAction.SetDateFilter -> TODO()
            is TransactionsViewAction.TransactionClick -> TODO()
            is TransactionsViewAction.ManageTransactionHeader -> onManageTransactionHeader(header = action.header)
        }
    }

    private fun onCreateNewTransactionClick() {
    }

    private fun onManageTransactionDate() {
    }

    private fun onSearchChange() {
    }

    private fun onSelectTab() {
    }

    private fun onSetAccountFilter() {
    }

    private fun onSetCategoryFilter() {
    }

    private fun onSetDateFilter() {
    }

    private fun onTransactionClick() {
    }

    private fun onManageTransactionHeader(header: TransactionHeader) {
        reduceState {
            copy(
                transactions = state.value.transactions.mapKeys { entry ->
                    entry.key.copy(isExpand = !header.isExpand)
                }
            )
        }
    }
}
