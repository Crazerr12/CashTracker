package ru.crazerr.cashtracker.feature.transactions.data.transactions.repository

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.account.model.AccountWithCurrency
import ru.crazerr.cashtracker.currency.domain.api.model.Currency
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.transactions.data.transactions.getAccounts.dataSource.GetAccountsLocalDataSource
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.AccountsRepository

internal class AccountsRepositoryImpl(
    private val getAccountsLocalDataSource: GetAccountsLocalDataSource,
) : AccountsRepository {
    override suspend fun getAccounts(): Result<List<Account>> =
        try {
            Result.success(getAccountsLocalDataSource.getAccounts().first().toAccounts())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
}

internal fun List<AccountWithCurrency>.toAccounts() =
    map {
        Account(
            id = it.id,
            name = it.name,
            balance = it.balance,
            currency = Currency(
                id = it.currency.id,
                name = it.currency.name,
                code = it.currency.code,
                symbol = it.currency.symbol,
                symbolNative = it.currency.symbolNative
            )
        )
    }
