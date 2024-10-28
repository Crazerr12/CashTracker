package ru.crazerr.cashtracker.feature.transaction.data.transaction.updateAccount.dataSource

import ru.crazerr.cashtracker.core.database.account.AccountDao
import ru.crazerr.cashtracker.core.database.account.AccountEntity
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account

internal class UpdateAccountLocalDataSource(
    private val accountDao: AccountDao,
) {
    suspend fun updateAccount(account: Account): Result<Unit> {
        return try {
            accountDao.update(account.toAccountEntity())
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}

internal fun Account.toAccountEntity() = AccountEntity(
    id = id,
    name = name,
    balance = balance,
    currencyId = currency.id,
)
