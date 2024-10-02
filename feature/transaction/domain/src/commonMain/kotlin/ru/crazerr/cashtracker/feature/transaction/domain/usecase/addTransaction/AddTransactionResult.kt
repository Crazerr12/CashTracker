package ru.crazerr.cashtracker.feature.transaction.domain.usecase.addTransaction

sealed interface AddTransactionResult {
    data object Ok : AddTransactionResult
    data class ValidationError(val throwable: Throwable) : AddTransactionResult
    data object NetworkError : AddTransactionResult
    data class UnknownError(val throwable: Throwable) : AddTransactionResult
}