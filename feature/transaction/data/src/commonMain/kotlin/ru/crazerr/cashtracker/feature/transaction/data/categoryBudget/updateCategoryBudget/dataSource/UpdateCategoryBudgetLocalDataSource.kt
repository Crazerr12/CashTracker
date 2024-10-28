package ru.crazerr.cashtracker.feature.transaction.data.categoryBudget.updateCategoryBudget.dataSource

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.database.budget.BudgetCategoryDao
import ru.crazerr.cashtracker.core.utils.dateTime.atEndOfMonth
import ru.crazerr.cashtracker.core.utils.dateTime.atStartOfMonth

internal class UpdateCategoryBudgetLocalDataSource(
    private val budgetCategoryDao: BudgetCategoryDao,
) {
    suspend fun updateBudgetCategory(
        categoryId: Long,
        date: LocalDate,
        amount: Float,
    ) {
        val budgetCategoryEntity = budgetCategoryDao.getByCategoryId(
            categoryId = categoryId,
            startOfMonthDate = date.atStartOfMonth().toString(),
            endOfMonthDate = date.atEndOfMonth().toString()
        )

        budgetCategoryDao.update(
            budgetCategoryEntity = budgetCategoryEntity.copy(
                lastTransactionDate = date,
                currentAmount = budgetCategoryEntity.currentAmount + amount,
            )
        )
    }
}
