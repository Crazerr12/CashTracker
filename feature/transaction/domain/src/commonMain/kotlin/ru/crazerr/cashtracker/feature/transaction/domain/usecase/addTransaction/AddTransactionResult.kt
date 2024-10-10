package ru.crazerr.cashtracker.feature.transaction.domain.usecase.addTransaction

sealed interface AddTransactionResult {
    data class Ok(val id: Long) : AddTransactionResult
    data object NetworkError : AddTransactionResult
    data class UnknownError(val throwable: Throwable) : AddTransactionResult
}