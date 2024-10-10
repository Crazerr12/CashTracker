package ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.dateTime.now
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

data class CreateTransactionState(
    val name: String,
    val amount: String,
    val amountError: String?,
    val transactionType: TransactionType,
    val date: LocalDate,
    val dateString: String,
    val showDatePicker: Boolean,
    val categoriesIsExpand: Boolean,
    val selectedCategory: Category,
    val categories: List<Category>,
    val accountsIsExpand: Boolean,
    val selectedAccount: Account,
    val accounts: List<Account>,
    val description: String?,
)

internal val InitialCreateTransactionState = CreateTransactionState(
    name = "",
    amount = "",
    amountError = null,
    transactionType = TransactionType.EXPENSE,
    date = LocalDate.now(),
    dateString = "",
    showDatePicker = false,
    categoriesIsExpand = false,
    selectedCategory = Category(
        id = 0,
        name = "",
    ),
    categories = emptyList(),
    accountsIsExpand = false,
    selectedAccount = Account(
        id = 0,
        name = "",
        balance = 0f,
        currency = "",
    ),
    accounts = emptyList(),
    description = null,
)