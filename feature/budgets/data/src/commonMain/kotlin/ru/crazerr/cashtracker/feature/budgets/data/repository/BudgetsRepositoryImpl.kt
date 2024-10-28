package ru.crazerr.cashtracker.feature.budgets.data.repository

import ru.crazerr.cashtracker.core.database.budget.model.BudgetCategoryWithCategory
import ru.crazerr.cashtracker.feature.budget.domain.api.model.BudgetCategory
import ru.crazerr.cashtracker.feature.budgets.data.deleteBudget.dataSource.DeleteBudgetLocalDataSource
import ru.crazerr.cashtracker.feature.budgets.data.getBudgets.dataSource.GetBudgetsLocalDataSource
import ru.crazerr.cashtracker.feature.budgets.domain.repository.BudgetsRepository
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

internal class BudgetsRepositoryImpl(
    private val getBudgetsLocalDataSource: GetBudgetsLocalDataSource,
    private val deleteBudgetLocalDataSource: DeleteBudgetLocalDataSource,
) : BudgetsRepository {
    override suspend fun getAllBudgets(): Result<List<BudgetCategory>> {
        return try {
            val budgetsCategory = getBudgetsLocalDataSource.getAllBudgets().toBudgets()
            Result.success(budgetsCategory)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun deleteBudgetById(budgetId: Long): Result<Unit> {
        return deleteBudgetLocalDataSource.deleteBudgetById(budgetId = budgetId)
    }
}

internal fun List<BudgetCategoryWithCategory>.toBudgets() =
    map {
        BudgetCategory(
            id = it.id,
            category = Category(
                id = it.category.id,
                name = it.category.name,
                iconId = it.category.iconId,
                color = it.category.color
            ),
            currentAmount = it.currentAmount,
            maxAmount = it.maxAmount,
            lastTransactionDate = it.lastTransactionDate,
            isRegular = it.isRegular,
            nextCreationDate = it.nextCreationDate,
        )
    }
