package ru.crazerr.cashtracker.feature.budget.data.addBudgetCategory.dataSource

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.database.budget.BudgetCategoryDao
import ru.crazerr.cashtracker.core.database.budget.BudgetCategoryEntity

internal class AddBudgetCategoryLocalDataSource(
    private val budgetCategoryDao: BudgetCategoryDao,
) {
    suspend fun addBudgetCategory(
        categoryId: Long,
        currentAmount: Float,
        maxAmount: Float,
        lastTransactionDate: LocalDate,
        isRegular: Boolean,
        nextCreationDate: LocalDate?,
    ): Result<Unit> {
        return try {
            budgetCategoryDao.insert(
                budgetCategoryEntity = BudgetCategoryEntity(
                    categoryId = categoryId,
                    currentAmount = currentAmount,
                    maxAmount = maxAmount,
                    lastTransactionDate = lastTransactionDate,
                    isRegular = isRegular,
                    nextCreationDate = nextCreationDate
                )
            )
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
