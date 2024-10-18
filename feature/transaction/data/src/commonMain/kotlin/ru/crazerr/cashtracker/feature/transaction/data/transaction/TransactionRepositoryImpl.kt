package ru.crazerr.cashtracker.feature.transaction.data.transaction

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.transaction.data.transaction.addTransaction.dataSource.AddTransactionLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.data.transaction.updateTransaction.dataSource.UpdateAccountLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.domain.repository.TransactionRepository

internal class TransactionRepositoryImpl(
    private val addTransactionLocalDataSource: AddTransactionLocalDataSource,
    private val updateAccountLocalDataSource: UpdateAccountLocalDataSource,
) : TransactionRepository {
    override suspend fun createTransaction(
        name: String,
        type: TransactionType,
        amount: Float,
        categoryId: Long,
        date: LocalDate,
        description: String?,
        account: Account,
    ): Result<Long> {
        updateAccountLocalDataSource.updateAccount(account = account)

        return addTransactionLocalDataSource.addTransaction(
            name = name,
            type = type,
            amount = amount,
            categoryId = categoryId,
            accountId = account.id,
            date = date,
            description = description
        )
    }
}
