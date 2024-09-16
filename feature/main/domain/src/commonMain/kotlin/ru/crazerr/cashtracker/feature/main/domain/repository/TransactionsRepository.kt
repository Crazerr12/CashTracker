package ru.crazerr.cashtracker.feature.main.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Month
import ru.crazerr.cashtracker.feature.main.domain.model.Transaction

interface TransactionsRepository {

    suspend fun getMonthlyTransactions(): Result<List<Transaction>>

}