package ru.crazerr.cashtracker.feature.account.presentation.createAccountDialog.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.currency.domain.api.model.Currency
import ru.crazerr.cashtracker.feature.account.domain.usecase.getCurrencies.GetCurrenciesResult
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponent

internal class GetCurrenciesResultHandler(
    private val result: GetCurrenciesResult,
    private val delegate: CreateAccountComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetCurrenciesResult.Ok -> onOk(currencies = result.currencies)
            GetCurrenciesResult.NetworkError -> onNetworkError()
            is GetCurrenciesResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(currencies: List<Currency>) {
        delegate.reduceState {
            copy(
                currencies = currencies,
                selectedCurrency = currencies.first { it.code == "RUB" }
            )
        }
    }
}
