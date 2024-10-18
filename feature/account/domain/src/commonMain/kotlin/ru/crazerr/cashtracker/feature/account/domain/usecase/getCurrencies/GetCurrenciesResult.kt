package ru.crazerr.cashtracker.feature.account.domain.usecase.getCurrencies

import ru.crazerr.cashtracker.currency.domain.api.model.Currency

sealed interface GetCurrenciesResult {
    data class Ok(val currencies: List<Currency>) : GetCurrenciesResult
    data object NetworkError : GetCurrenciesResult
    data class UnknownError(val throwable: Throwable) : GetCurrenciesResult
}
