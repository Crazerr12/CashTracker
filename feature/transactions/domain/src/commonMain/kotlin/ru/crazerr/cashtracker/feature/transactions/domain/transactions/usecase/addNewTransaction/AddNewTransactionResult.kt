package ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.addNewTransaction

sealed interface AddNewTransactionResult {
    data object Ok : AddNewTransactionResult
    data object NetworkError : AddNewTransactionResult
    data class UnknownError(val throwable: Throwable) : AddNewTransactionResult
}