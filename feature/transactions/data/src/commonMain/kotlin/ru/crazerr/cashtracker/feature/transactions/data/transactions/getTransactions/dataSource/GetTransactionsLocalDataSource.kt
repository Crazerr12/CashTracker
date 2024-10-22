package ru.crazerr.cashtracker.feature.transactions.data.transactions.getTransactions.dataSource

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.transaction.TransactionDao
import ru.crazerr.cashtracker.core.database.transaction.model.TransactionWithCategoryAndAccountDbo

internal class GetTransactionsLocalDataSource(
    private val transactionDao: TransactionDao,
) {
    fun getTransactionsByDateAndQueryAndPaged(
        query: String,
        startDate: String,
        endDate: String,
        categoryIds: List<Long>,
        accountIds: List<Long>,
        limit: Int,
        offset: Int,
    ): Flow<List<TransactionWithCategoryAndAccountDbo>> {
        return transactionDao.getPagedTransactionsByDateCategoriesAndQuery(
            query = query,
            startDate = startDate,
            endDate = endDate,
            categoryIds = categoryIds,
            accountIds = accountIds,
            limit = limit,
            offset = offset,
        )
    }
}
