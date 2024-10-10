package ru.crazerr.cashtracker.feature.account.domain.usecase.addAccount

sealed interface AddAccountResult {
    data class Ok(val id: Long) : AddAccountResult
    data object NetworkError : AddAccountResult
    data class UnknownError(val throwable: Throwable) : AddAccountResult
}