package ru.crazerr.cashtracker.feature.main.presentation.main

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.dateTime.now
import ru.crazerr.cashtracker.core.utils.model.RegularType
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.main.domain.model.Account
import ru.crazerr.cashtracker.feature.main.domain.model.Transaction

data class MainState(
    val currentDate: LocalDate,
    val transactions: List<Transaction>,
    val accounts: List<Account>,
    val itemsToShow: Int,
    val itemsToShowIsExpanded: Boolean,
    val newTransactionDialogIsShow: Boolean,
    val newTransactionTitle: String,
    val newTransactionType: TransactionType?,
    val newTransactionSumma: Float,
    val newTransactionCurrency: String,
    val newTransactionCategoryId: Long,
    val newTransactionDate: LocalDate,
    val newTransactionAccountId: Long,
    val newTransactionDescription: String?,
    val newTransactionIsRegular: Boolean,
    val newTransactionRegularType: RegularType?,
    val newTransactionGoalId: Long?,
)

internal val InitialMainState = MainState(
    currentDate = LocalDate.now(),
    transactions = emptyList(),
    accounts = emptyList(),
    itemsToShow = 10,
    itemsToShowIsExpanded = false,
    newTransactionDialogIsShow = false,
    newTransactionTitle = "",
    newTransactionType = null,
    newTransactionSumma = 0f,
    newTransactionCurrency = "ru",
    newTransactionCategoryId = 0,
    newTransactionDate = LocalDate.now(),
    newTransactionAccountId = 0,
    newTransactionDescription = null,
    newTransactionIsRegular = false,
    newTransactionRegularType = RegularType.EVERY_DAY,
    newTransactionGoalId = null
)