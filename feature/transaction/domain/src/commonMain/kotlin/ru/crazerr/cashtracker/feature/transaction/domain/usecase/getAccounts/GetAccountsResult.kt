package ru.crazerr.cashtracker.feature.transaction.domain.usecase.getAccounts

import ru.crazerr.cashtracker.feature.account.domain.api.model.Account

sealed interface GetAccountsResult {
    data class Ok(val accounts: List<Account>) : GetAccountsResult
    data object NetworkError : GetAccountsResult
    data class UnknownError(val throwable: Throwable) : GetAccountsResult
}