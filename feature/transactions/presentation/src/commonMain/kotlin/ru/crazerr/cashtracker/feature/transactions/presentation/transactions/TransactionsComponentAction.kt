package ru.crazerr.cashtracker.feature.transactions.presentation.transactions

sealed interface TransactionsComponentAction {
    data object BackClick : TransactionsComponentAction
}
