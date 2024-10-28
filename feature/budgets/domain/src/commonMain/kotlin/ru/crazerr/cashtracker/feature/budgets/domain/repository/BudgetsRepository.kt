package ru.crazerr.cashtracker.feature.budgets.domain.repository

import ru.crazerr.cashtracker.feature.budget.domain.api.model.BudgetCategory

interface BudgetsRepository {
    suspend fun getAllBudgets(): Result<List<BudgetCategory>>

    suspend fun deleteBudgetById(budgetId: Long): Result<Unit>
}
