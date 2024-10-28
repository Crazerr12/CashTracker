package ru.crazerr.cashtracker.feature.budgets.domain.usecase.deletBudgetById

sealed interface DeleteBudgetResult {
    data class Ok(val id: Long) : DeleteBudgetResult
    data object NetworkError : DeleteBudgetResult
    data class UnknownError(val throwable: Throwable) : DeleteBudgetResult
}
