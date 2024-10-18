package ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog

import ru.crazerr.cashtracker.currency.domain.api.model.Currency

sealed interface CreateAccountViewAction {
    data object SaveClick : CreateAccountViewAction
    data object CancelClick : CreateAccountViewAction
    data class UpdateName(val name: String) : CreateAccountViewAction
    data class UpdateCurrency(val currency: Currency) : CreateAccountViewAction
    data class UpdateDescription(val description: String) : CreateAccountViewAction
    data class UpdateCurrentAmount(val amount: String) : CreateAccountViewAction
    data object ManageCurrencyMenu : CreateAccountViewAction
}
