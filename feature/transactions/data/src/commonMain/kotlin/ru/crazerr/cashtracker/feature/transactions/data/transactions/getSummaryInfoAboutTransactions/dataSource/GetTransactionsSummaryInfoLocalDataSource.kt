package ru.crazerr.cashtracker.feature.transactions.data.transactions.getSummaryInfoAboutTransactions.dataSource

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.transaction.TransactionDao
import ru.crazerr.cashtracker.core.database.transaction.model.CategoryShareDbo
import ru.crazerr.cashtracker.core.database.transaction.model.TransactionsSummaryInfoDbo

internal class GetTransactionsSummaryInfoLocalDataSource(
    private val transactionDao: TransactionDao,
) {
    fun getTransactionsSummaryInfo(
        startDate: String,
        endDate: String,
        query: String,
        accountIds: List<Long>,
        categoryIds: List<Long>,
    ): Flow<TransactionsSummaryInfoDbo> {
        return transactionDao.getTransactionsSummaryInfo(
            startDate = startDate,
            endDate = endDate,
            categoryIds = categoryIds,
            accountIds = accountIds,
            query = query
        )
    }

    fun getMostExpensiveCategories(
        startDate: String,
        endDate: String,
        query: String,
        accountIds: List<Long>,
        categoryIds: List<Long>,
    ): Flow<List<CategoryShareDbo>> {
        return transactionDao.getMostExpensiveCategories(
            startDate = startDate,
            endDate = endDate,
            categoryIds = categoryIds,
            accountIds = accountIds,
            query = query
        )
    }
}
