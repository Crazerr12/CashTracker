package ru.crazerr.cashtracker.feature.transactions.presentation.transactions.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getAccounts.GetAccountsResult
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsComponent

internal class GetAccountsResultHandler(
    private val result: GetAccountsResult,
    private val delegate: TransactionsComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetAccountsResult.Ok -> onOk(accounts = result.accounts)
            GetAccountsResult.NetworkError -> onNetworkError()
            is GetAccountsResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(accounts: List<Account>) {
        delegate.reduceState {
            copy(
                accountsFilter = accounts.associateBy(
                    keySelector = { it },
                    valueTransform = { true }
                )
            )
        }
    }
}
