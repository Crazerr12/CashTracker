package ru.crazerr.cashtracker.feature.budgets.domain.usecase.getBudgets

import ru.crazerr.cashtracker.feature.budget.domain.api.model.BudgetCategory

sealed interface GetBudgetsResult {
    data class Ok(val budgets: List<BudgetCategory>) : GetBudgetsResult
    data object NetworkError : GetBudgetsResult
    data class UnknownError(val throwable: Throwable) : GetBudgetsResult
}
