package ru.crazerr.cashtracker.feature.main.domain.repository

import ru.crazerr.cashtracker.feature.main.domain.model.Transaction

interface TransactionsRepository {

    suspend fun getMonthlyTransactions(): Result<List<Transaction>>
}
