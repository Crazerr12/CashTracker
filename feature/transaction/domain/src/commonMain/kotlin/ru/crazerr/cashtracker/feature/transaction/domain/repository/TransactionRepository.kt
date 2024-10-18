package ru.crazerr.cashtracker.feature.transaction.domain.repository

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account

interface TransactionRepository {
    suspend fun createTransaction(
        name: String,
        type: TransactionType,
        amount: Float,
        categoryId: Long,
        date: LocalDate,
        description: String?,
        account: Account,
    ): Result<Long>
}
