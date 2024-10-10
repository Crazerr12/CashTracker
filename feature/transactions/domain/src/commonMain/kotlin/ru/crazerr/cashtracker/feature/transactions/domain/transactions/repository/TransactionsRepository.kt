package ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.Account
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.Category
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.Transaction

interface TransactionsRepository {

    suspend fun createNewTransaction(transaction: Transaction): Result<String>

    fun getTransactions(
        query: String,
        categories: List<Category>,
        date: LocalDate,
        accounts: List<Account>,
    ): Result<Flow<Transaction>>
}
