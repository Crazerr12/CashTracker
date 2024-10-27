package ru.crazerr.cashtracker.feature.budget.data.getTransactionsAmountAndLastDate.dataSource

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.database.transaction.TransactionDao
import ru.crazerr.cashtracker.core.database.transaction.model.BudgetCategoryStats

class BudgetCategoryStatsLocalDataSource(
    private val transactionDao: TransactionDao,
) {
    suspend fun getTransactionCategoryStats(
        categoryId: Long,
        startDate: LocalDate,
        endDate: LocalDate,
    ): BudgetCategoryStats {
        return transactionDao.getTransactionBudgetCategoryStats(
            categoryId = categoryId,
            startDate = startDate.toString(),
            endDate = endDate.toString()
        )
    }
}
