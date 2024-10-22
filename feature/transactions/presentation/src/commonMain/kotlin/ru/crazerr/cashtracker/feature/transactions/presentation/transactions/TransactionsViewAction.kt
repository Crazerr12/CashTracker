package ru.crazerr.cashtracker.feature.transactions.presentation.transactions

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.TransactionHeader
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.model.TransactionsTab

sealed interface TransactionsViewAction {
    data class SearchChange(val query: String) : TransactionsViewAction
    data class SelectTab(val tab: TransactionsTab) : TransactionsViewAction
    data class SetStartDateFilter(val date: LocalDate) : TransactionsViewAction
    data class SetEndDateFilter(val date: LocalDate) : TransactionsViewAction
    data class TransactionClick(val transactionId: Long) : TransactionsViewAction
    data class ManageTransactionHeader(val header: TransactionHeader) : TransactionsViewAction
    data class AllCategoriesCheckboxClick(val isAllChecked: Boolean) : TransactionsViewAction
    data class CategoryCheckboxClick(val category: Category) : TransactionsViewAction
    data class AllAccountsCheckboxClick(val isAllChecked: Boolean) : TransactionsViewAction
    data class AccountCheckboxClick(val account: Account) : TransactionsViewAction

    data object CreateNewTransactionClick : TransactionsViewAction
    data object ManageDateFilterDropdown : TransactionsViewAction
    data object ManageCategoriesFilterDropdown : TransactionsViewAction
    data object ManageAccountsFilterDropdown : TransactionsViewAction
}
