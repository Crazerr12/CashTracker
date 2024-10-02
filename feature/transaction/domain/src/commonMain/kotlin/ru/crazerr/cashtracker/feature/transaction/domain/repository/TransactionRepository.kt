package ru.crazerr.cashtracker.feature.transaction.domain.repository

import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction

interface TransactionRepository {

    suspend fun createTransaction(transaction: Transaction): Result<Unit>
}