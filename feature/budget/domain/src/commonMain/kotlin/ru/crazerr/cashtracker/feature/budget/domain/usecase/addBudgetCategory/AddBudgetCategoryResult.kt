package ru.crazerr.cashtracker.feature.budget.domain.usecase.addBudgetCategory

sealed interface AddBudgetCategoryResult {
    data object Ok : AddBudgetCategoryResult
    data object NetworkError : AddBudgetCategoryResult
    data class UnknownError(val throwable: Throwable) : AddBudgetCategoryResult
}
