package ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog

data class CreateAccountState(
    val name: String,
    val balance: String,
    val currency: String,
    val description: String,
    val symbol: String,
    val balanceError: String?,
    val buttonLoading: Boolean,
    val isExpanded: Boolean,
)

internal val InitialCreateAccountState = CreateAccountState(
    name = "",
    balance = "",
    currency = "RUB",
    description = "",
    symbol = "",
    balanceError = null,
    buttonLoading = false,
    isExpanded = false,
)
