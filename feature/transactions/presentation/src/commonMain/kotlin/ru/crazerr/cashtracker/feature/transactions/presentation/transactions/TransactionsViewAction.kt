package ru.crazerr.cashtracker.feature.transactions.presentation.transactions

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.TransactionHeader
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.model.TransactionsTab

sealed interface TransactionsViewAction {
    data class SearchChange(val query: String) : TransactionsViewAction
    data class SelectTab(val tab: TransactionsTab) : TransactionsViewAction
    data class SetDateFilter(val date: LocalDate) : TransactionsViewAction
    data class SetCategoryFilter(val category: String) : TransactionsViewAction
    data class SetAccountFilter(val account: String) : TransactionsViewAction
    data class TransactionClick(val transaction: String) : TransactionsViewAction
    data class ManageTransactionHeader(val header: TransactionHeader) : TransactionsViewAction
    data object CreateNewTransactionClick : TransactionsViewAction
    data object ManageTransactionsDate : TransactionsViewAction
}
