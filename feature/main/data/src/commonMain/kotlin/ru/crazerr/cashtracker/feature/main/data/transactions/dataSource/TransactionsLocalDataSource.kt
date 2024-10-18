package ru.crazerr.cashtracker.feature.main.data.transactions.dataSource

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.transaction.TransactionDao
import ru.crazerr.cashtracker.core.database.transaction.model.CategoryShareDbo
import ru.crazerr.cashtracker.core.database.transaction.model.ExpensesAndIncomeDbo
import ru.crazerr.cashtracker.core.database.transaction.model.TransactionWithCategoryAndAccountDbo

internal class TransactionsLocalDataSource(
    private val transactionDao: TransactionDao,
) {
    fun getMonthlyTransactions(
        date: String,
        limit: Int,
    ): Flow<List<TransactionWithCategoryAndAccountDbo>> {
        return transactionDao.getByMonthWithLimit(date = date, limit = limit)
    }

    fun getCategoryShares(
        date: String,
    ): Flow<List<CategoryShareDbo>> {
        return transactionDao.getCategoryShares(date = date)
    }

    fun getExpensesAndIncomeByMonth(date: String): Flow<ExpensesAndIncomeDbo> {
        return transactionDao.getExpensesAndIncomeByMonth(date = date)
    }
}
