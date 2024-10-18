package ru.crazerr.cashtracker.feature.transaction.data.account.getAccounts.dataSource

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.account.AccountDao
import ru.crazerr.cashtracker.core.database.account.model.AccountWithCurrency
import ru.crazerr.cashtracker.currency.domain.api.model.Currency
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account

internal class GetAccountsLocalDataSource(
    private val accountDao: AccountDao,
) {
    suspend fun getAccounts(): Result<List<Account>> {
        return try {
            val accountEntities = accountDao.getAll().first()
            Result.success(accountEntities.toAccounts())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}

internal fun List<AccountWithCurrency>.toAccounts() =
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
