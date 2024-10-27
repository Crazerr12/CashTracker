package ru.crazerr.cashtracker.feature.budget.data.repository

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.dateTime.atCurrentDayOfMonth
import ru.crazerr.cashtracker.core.utils.dateTime.atStartOfMonth
import ru.crazerr.cashtracker.core.utils.dateTime.now
import ru.crazerr.cashtracker.feature.budget.data.addBudgetCategory.dataSource.AddBudgetCategoryLocalDataSource
import ru.crazerr.cashtracker.feature.budget.data.getTransactionsAmountAndLastDate.dataSource.BudgetCategoryStatsLocalDataSource
import ru.crazerr.cashtracker.feature.budget.domain.repository.BudgetCategoriesRepository

internal class BudgetCategoriesRepositoryImpl(
    private val addBudgetCategoryLocalDataSource: AddBudgetCategoryLocalDataSource,
    private val budgetCategoryStatsLocalDataSource: BudgetCategoryStatsLocalDataSource,
) : BudgetCategoriesRepository {
    override suspend fun addBudgetCategory(
        categoryId: Long,
        maxAmount: Float,
        isRegular: Boolean,
        nextCreationDate: LocalDate?,
    ): Result<Unit> {
        val categoryStats = budgetCategoryStatsLocalDataSource.getTransactionCategoryStats(
            categoryId = categoryId,
            startDate = LocalDate.now().atStartOfMonth(),
            endDate = LocalDate.now().atCurrentDayOfMonth(),
        )

        return addBudgetCategoryLocalDataSource.addBudgetCategory(
            categoryId = categoryId,
            currentAmount = categoryStats.currentAmount,
            maxAmount = maxAmount,
            lastTransactionDate = categoryStats.lastTransactionDate,
            isRegular = isRegular,
            nextCreationDate = nextCreationDate
        )
    }
}
