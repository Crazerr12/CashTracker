package ru.crazerr.cashtracker.feature.main.presentation.main

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.dateTime.now
import ru.crazerr.cashtracker.feature.main.domain.model.Account
import ru.crazerr.cashtracker.feature.main.domain.model.Transaction

data class MainState(
    val currentDate: LocalDate,
    val transactions: List<Transaction>,
    val accounts: List<Account>,
    val itemsToShow: Int,
    val itemsToShowIsExpanded: Boolean,
)

internal val InitialMainState = MainState(
    currentDate = LocalDate.now(),
    transactions = emptyList(),
    accounts = emptyList(),
    itemsToShow = 10,
    itemsToShowIsExpanded = false
)