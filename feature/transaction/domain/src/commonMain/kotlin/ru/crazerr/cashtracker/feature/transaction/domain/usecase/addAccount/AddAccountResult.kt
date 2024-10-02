package ru.crazerr.cashtracker.feature.transaction.domain.usecase.addAccount

sealed interface AddAccountResult {
    data object Ok : AddAccountResult
    data class ValidationError(val throwable: Throwable) : AddAccountResult
    data object NetworkError : AddAccountResult
    data class UnknownError(val throwable: Throwable) : AddAccountResult
}