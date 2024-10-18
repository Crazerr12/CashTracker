package ru.crazerr.cashtracker.feature.main.domain.repository

import ru.crazerr.cashtracker.feature.main.domain.model.CategoryShare
import ru.crazerr.cashtracker.feature.main.domain.model.ExpensesAndIncome
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction

interface TransactionsRepository {

    suspend fun getMonthlyTransactions(date: String, limit: Int): Result<List<Transaction>>

    suspend fun getCategoryShares(date: String): Result<List<CategoryShare>>

    suspend fun getExpensesAndIncomeByMonth(date: String): Result<ExpensesAndIncome>
}
