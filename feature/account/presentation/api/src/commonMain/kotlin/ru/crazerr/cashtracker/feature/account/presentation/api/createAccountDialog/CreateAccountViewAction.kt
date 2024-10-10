package ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog

sealed interface CreateAccountViewAction {
    data object SaveClick : CreateAccountViewAction
    data object CancelClick : CreateAccountViewAction
    data class UpdateName(val name: String) : CreateAccountViewAction
    data class UpdateCurrency(val currency: String) : CreateAccountViewAction
    data class UpdateDescription(val description: String) : CreateAccountViewAction
    data class UpdateCurrentAmount(val amount: String) : CreateAccountViewAction
    data object ManageCurrencyMenu : CreateAccountViewAction
}
