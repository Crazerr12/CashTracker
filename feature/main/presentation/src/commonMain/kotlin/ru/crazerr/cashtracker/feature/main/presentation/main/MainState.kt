package ru.crazerr.cashtracker.feature.main.presentation.main

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.dateTime.now
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.main.domain.model.CategoryShare
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction

data class MainState(
    val currentDate: LocalDate,
    val transactions: List<Transaction>,
    val accounts: List<Account>,
    val categoryShares: List<CategoryShare>,
    val transactionsToShow: Int,
    val itemsToShowIsExpanded: Boolean,
    val currentBalance: Float,
    val expensesByMonth: Float,
    val incomeByMonth: Float,
)

internal val InitialMainState = MainState(
    currentDate = LocalDate.now(),
    transactions = emptyList(),
    accounts = emptyList(),
    categoryShares = emptyList(),
    transactionsToShow = 10,
    itemsToShowIsExpanded = false,
    currentBalance = 0f,
    expensesByMonth = 0f,
    incomeByMonth = 0f,
)
