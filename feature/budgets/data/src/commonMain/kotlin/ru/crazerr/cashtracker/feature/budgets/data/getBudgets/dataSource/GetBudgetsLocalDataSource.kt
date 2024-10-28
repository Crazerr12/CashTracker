package ru.crazerr.cashtracker.feature.budgets.data.getBudgets.dataSource

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.budget.BudgetCategoryDao
import ru.crazerr.cashtracker.core.database.budget.model.BudgetCategoryWithCategory

class GetBudgetsLocalDataSource(
    private val budgetCategoryDao: BudgetCategoryDao,
) {
    suspend fun getAllBudgets(): List<BudgetCategoryWithCategory> {
        return budgetCategoryDao.getAll().first()
    }
}
