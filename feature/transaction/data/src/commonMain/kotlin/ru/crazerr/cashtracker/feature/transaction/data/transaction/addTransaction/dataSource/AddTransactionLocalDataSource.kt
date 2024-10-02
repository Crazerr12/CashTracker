package ru.crazerr.cashtracker.feature.transaction.data.transaction.addTransaction.dataSource

import ru.crazerr.cashtracker.core.database.transaction.TransactionDao
import ru.crazerr.cashtracker.core.database.transaction.TransactionEntity
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction

internal class AddTransactionLocalDataSource(
    private val transactionDao: TransactionDao,
) {
    suspend fun addTransaction(transaction: Transaction): Result<Unit> {
        return try {
            transactionDao.insert(
                transactionEntity = arrayOf(
                    transaction.toTransactionEntity()
                )
            )
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}

internal fun Transaction.toTransactionEntity() =
    TransactionEntity(
        id = id,
        name = name,
        amount = amount,
        type = type,
        date = date,
        categoryId = category.id,
        accountId = account.id,
        description = description
    )
