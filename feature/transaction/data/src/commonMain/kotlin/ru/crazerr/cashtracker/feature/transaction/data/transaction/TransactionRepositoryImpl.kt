package ru.crazerr.cashtracker.feature.transaction.data.transaction

import ru.crazerr.cashtracker.feature.transaction.data.transaction.addTransaction.dataSource.AddTransactionLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction
import ru.crazerr.cashtracker.feature.transaction.domain.repository.TransactionRepository

internal class TransactionRepositoryImpl(
    private val addTransactionLocalDataSource: AddTransactionLocalDataSource,
) : TransactionRepository {
    override suspend fun createTransaction(transaction: Transaction): Result<Unit> {
        return addTransactionLocalDataSource.addTransaction(transaction = transaction)
    }
}