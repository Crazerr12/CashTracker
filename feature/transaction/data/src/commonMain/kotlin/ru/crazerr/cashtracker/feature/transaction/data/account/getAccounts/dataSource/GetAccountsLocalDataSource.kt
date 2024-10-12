package ru.crazerr.cashtracker.feature.transaction.data.account.getAccounts.dataSource

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.account.AccountDao
import ru.crazerr.cashtracker.core.database.account.AccountEntity
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

internal fun List<AccountEntity>.toAccounts() =
    map {
        Account(
            id = it.id,
            name = it.name,
            balance = it.balance,
            currency = it.currency
        )
    }
