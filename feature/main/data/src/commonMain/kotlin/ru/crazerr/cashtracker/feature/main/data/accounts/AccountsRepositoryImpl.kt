package ru.crazerr.cashtracker.feature.main.data.accounts

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.account.model.AccountWithCurrency
import ru.crazerr.cashtracker.currency.domain.api.model.Currency
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.AccountsDataSource
import ru.crazerr.cashtracker.feature.main.domain.repository.AccountsRepository

internal class AccountsRepositoryImpl(
    private val accountsDataSource: AccountsDataSource,
) : AccountsRepository {
    override suspend fun getAllAccounts(): Result<List<Account>> {
        return try {
            val accountEntities = accountsDataSource.getAccounts().first()
            val accounts = accountEntities.toAccounts()
            Result.success(accounts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

fun List<AccountWithCurrency>.toAccounts() =
    map { accountWithCurrency ->
        Account(
            id = accountWithCurrency.id,
            name = accountWithCurrency.name,
            balance = accountWithCurrency.balance,
            currency = Currency(
                id = accountWithCurrency.currency.id,
                name = accountWithCurrency.currency.name,
                code = accountWithCurrency.currency.code,
                symbol = accountWithCurrency.currency.symbol,
                symbolNative = accountWithCurrency.currency.symbolNative
            )
        )
    }
