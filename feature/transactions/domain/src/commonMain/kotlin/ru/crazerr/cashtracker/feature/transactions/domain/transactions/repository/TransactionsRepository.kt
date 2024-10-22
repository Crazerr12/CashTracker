package ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.SummaryInfo

interface TransactionsRepository {

    suspend fun getTransactionsByDateAndCategoriesAndAccountsPaged(
        query: String,
        startDate: String,
        endDate: String,
        categoryIds: List<Long>,
        accountIds: List<Long>,
        limit: Int,
        offset: Int,
    ): Flow<List<Transaction>>

    fun getTransactionsSummaryInfo(
        query: String,
        startDate: String,
        endDate: String,
        categoryIds: List<Long>,
        accountIds: List<Long>,
    ): Result<Flow<SummaryInfo>>
}
