package ru.crazerr.cashtracker.feature.main.presentation.main.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.main.domain.model.Account
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsResult
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponent

class GetAccountsResultHandler(
    private val result: GetAccountsResult,
    private val delegate: MainComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetAccountsResult.Ok -> onOk(result.accounts)
            GetAccountsResult.NetworkError -> onNetworkError()
            is GetAccountsResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(accounts: List<Account>) {
        delegate.reduceState {
            copy(
                accounts = accounts
            )
        }
    }
}
