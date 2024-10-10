package ru.crazerr.cashtracker.feature.transaction.data.transaction

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.transaction.data.transaction.addTransaction.dataSource.AddTransactionLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.domain.repository.TransactionRepository


internal class TransactionRepositoryImpl(
    private val addTransactionLocalDataSource: AddTransactionLocalDataSource,
) : TransactionRepository {
    override suspend fun createTransaction(
        name: String,
        type: TransactionType,
        amount: Float,
        categoryId: Long,
        accountId: Long,
        date: LocalDate,
        description: String?,
    ): Result<Long> {
        return addTransactionLocalDataSource.addTransaction(
            name = name,
            type = type,
            amount = amount,
            categoryId = categoryId,
            accountId = accountId,
            date = date,
            description = description
        )
    }
}