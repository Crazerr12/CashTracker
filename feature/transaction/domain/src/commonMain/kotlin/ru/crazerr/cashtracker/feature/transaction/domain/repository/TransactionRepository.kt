package ru.crazerr.cashtracker.feature.transaction.domain.repository

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.model.TransactionType

interface TransactionRepository {

    suspend fun createTransaction(
        name: String,
        type: TransactionType,
        amount: Float,
        categoryId: Long,
        accountId: Long,
        date: LocalDate,
        description: String?,
    ): Result<Long>
}