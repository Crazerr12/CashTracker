package ru.crazerr.cashtracker.feature.transactions.presentation.transactions

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.dateTime.atCurrentDayOfMonth
import ru.crazerr.cashtracker.core.utils.dateTime.atStartOfMonth
import ru.crazerr.cashtracker.core.utils.dateTime.now
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.CategoryShare
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.model.TransactionsTab

data class TransactionsState(
    val transactions: Flow<PagingData<Transaction>>,
    val selectedTab: TransactionsTab,
    val query: String,
    val startDateFilter: LocalDate,
    val endDateFilter: LocalDate,
    val dateFilterIsExpanded: Boolean,
    val categoriesFilter: Map<Category, Boolean>,
    val categoriesFilterIsExpanded: Boolean,
    val allCategoriesChecked: Boolean,
    val accountsFilter: Map<Account, Boolean>,
    val accountsFilterIsExpanded: Boolean,
    val allAccountsChecked: Boolean,
    val summaryIncome: Float,
    val summaryExpenses: Float,
    val mostExpensiveCategories: List<CategoryShare>,
)

internal val InitialTransactionsState = TransactionsState(
    transactions = emptyFlow(),
    selectedTab = TransactionsTab.All,
    query = "",
    startDateFilter = LocalDate.now().atStartOfMonth(),
    endDateFilter = LocalDate.now().atCurrentDayOfMonth(),
    dateFilterIsExpanded = false,
    categoriesFilter = emptyMap(),
    categoriesFilterIsExpanded = false,
    allCategoriesChecked = true,
    accountsFilter = emptyMap(),
    accountsFilterIsExpanded = false,
    allAccountsChecked = true,
    summaryIncome = 0f,
    summaryExpenses = 0f,
    mostExpensiveCategories = emptyList()
)
