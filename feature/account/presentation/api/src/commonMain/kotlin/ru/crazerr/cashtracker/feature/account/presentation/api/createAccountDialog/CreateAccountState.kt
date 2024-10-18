package ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog

import ru.crazerr.cashtracker.currency.domain.api.model.Currency

data class CreateAccountState(
    val name: String,
    val balance: String,
    val selectedCurrency: Currency,
    val currencies: List<Currency>,
    val description: String,
    val symbol: String,
    val balanceError: String?,
    val buttonLoading: Boolean,
    val isExpanded: Boolean,
)

internal val InitialCreateAccountState = CreateAccountState(
    name = "",
    balance = "",
    selectedCurrency = Currency(
        id = 0,
        name = "",
        code = "",
        symbol = "",
        symbolNative = "",
    ),
    currencies = emptyList(),
    description = "",
    symbol = "",
    balanceError = null,
    buttonLoading = false,
    isExpanded = false,
)
