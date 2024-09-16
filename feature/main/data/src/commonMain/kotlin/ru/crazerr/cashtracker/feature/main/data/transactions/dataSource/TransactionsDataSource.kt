package ru.crazerr.cashtracker.feature.main.data.transactions.dataSource

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Month
import ru.crazerr.cashtracker.core.database.transaction.TransactionDao
import ru.crazerr.cashtracker.core.database.transaction.TransactionEntity

internal class TransactionsDataSource(
    private val transactionDao: TransactionDao,
) {

    fun getMonthlyTransactions(): Flow<List<TransactionEntity>> {
        return transactionDao.getByMonth()
    }
}