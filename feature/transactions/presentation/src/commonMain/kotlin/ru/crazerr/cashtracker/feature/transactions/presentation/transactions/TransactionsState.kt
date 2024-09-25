package ru.crazerr.cashtracker.feature.transactions.presentation.transactions

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.dateTime.now
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.Transaction
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.TransactionHeader
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.model.TransactionsTab

data class TransactionsState(
    val transactions: Map<TransactionHeader, List<Transaction>>,
    val selectedTab: TransactionsTab,
    val searchQuery: String,
    val selectedDate: LocalDate,
    val selectedCategories: List<String>,
    val selectedAccounts: List<String>,
    val summaryIncome: Float,
    val summaryExpenses: Float,
    val mostExpensiveCategories: Map<String, Int>,
)

internal val InitialTransactionsState = TransactionsState(
    transactions = emptyMap(),
    selectedTab = TransactionsTab.All,
    searchQuery = "",
    selectedDate = LocalDate.now(),
    selectedCategories = emptyList(),
    selectedAccounts = emptyList(),
    summaryIncome = 0f,
    summaryExpenses = 0f,
    mostExpensiveCategories = emptyMap()
)
