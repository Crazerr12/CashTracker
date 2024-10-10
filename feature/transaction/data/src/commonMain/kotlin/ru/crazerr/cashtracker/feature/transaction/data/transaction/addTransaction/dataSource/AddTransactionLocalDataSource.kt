package ru.crazerr.cashtracker.feature.transaction.data.transaction.addTransaction.dataSource

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.database.transaction.TransactionDao
import ru.crazerr.cashtracker.core.database.transaction.TransactionEntity
import ru.crazerr.cashtracker.core.utils.model.TransactionType

internal class AddTransactionLocalDataSource(
    private val transactionDao: TransactionDao,
) {
    suspend fun addTransaction(
        name: String,
        type: TransactionType,
        amount: Float,
        categoryId: Long,
        accountId: Long,
        date: LocalDate,
        description: String?,
    ): Result<Long> {
        return try {
            val transactionId = transactionDao.insert(
                transactionEntity = TransactionEntity(
                    name = name,
                    amount = amount,
                    type = type,
                    date = date,
                    categoryId = categoryId,
                    accountId = accountId,
                    description = description
                )
            )
            Result.success(transactionId)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
