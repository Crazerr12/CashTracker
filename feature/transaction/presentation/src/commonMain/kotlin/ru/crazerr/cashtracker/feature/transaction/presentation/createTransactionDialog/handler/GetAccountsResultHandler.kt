package ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.getAccounts.GetAccountsResult
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponent

internal class GetAccountsResultHandler(
    private val result: GetAccountsResult,
    private val delegate: CreateTransactionComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetAccountsResult.Ok -> onOk(accounts = result.accounts)
            GetAccountsResult.NetworkError -> onNetworkError()
            is GetAccountsResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(accounts: List<Account>) {
        if (accounts.isNotEmpty()) {
            delegate.reduceState {
                copy(accounts = accounts, selectedAccount = accounts[0])
            }
        }
    }
}