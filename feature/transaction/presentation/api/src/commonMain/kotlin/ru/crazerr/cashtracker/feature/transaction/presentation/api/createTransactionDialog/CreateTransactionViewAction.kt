package ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.model.TransactionType
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

sealed interface CreateTransactionViewAction {
    data class NameChange(val name: String) : CreateTransactionViewAction
    data class AmountChange(val amount: String) : CreateTransactionViewAction
    data class SetTransactionType(val transactionType: TransactionType) :
        CreateTransactionViewAction

    data class SetDate(val date: LocalDate) : CreateTransactionViewAction
    data class SelectCategory(val category: Category) : CreateTransactionViewAction
    data class SelectAccount(val account: Account) : CreateTransactionViewAction
    data object ManageCategoriesDropdownMenu : CreateTransactionViewAction
    data object ManageAccountsDropdownMenu : CreateTransactionViewAction
    data class DescriptionChange(val description: String) : CreateTransactionViewAction
    data object CancelClick : CreateTransactionViewAction
    data object SaveTransactionClick : CreateTransactionViewAction
    data object CreateNewAccount : CreateTransactionViewAction
    data object CreateNewCategory : CreateTransactionViewAction
    data object ManageDatePicker : CreateTransactionViewAction
    data class UpdateDateString(val dateString: String) : CreateTransactionViewAction
}