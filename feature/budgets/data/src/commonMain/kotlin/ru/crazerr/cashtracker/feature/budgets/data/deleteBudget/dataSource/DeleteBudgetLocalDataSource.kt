package ru.crazerr.cashtracker.feature.budgets.data.deleteBudget.dataSource

import ru.crazerr.cashtracker.core.database.budget.BudgetCategoryDao

internal class DeleteBudgetLocalDataSource(
    private val budgetCategoryDao: BudgetCategoryDao,
) {
    suspend fun deleteBudgetById(budgetId: Long): Result<Unit> {
        return try {
            budgetCategoryDao.deleteById(id = budgetId)
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
