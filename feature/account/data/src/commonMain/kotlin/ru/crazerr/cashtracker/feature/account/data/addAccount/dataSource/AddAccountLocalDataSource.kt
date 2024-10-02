package ru.crazerr.cashtracker.feature.account.data.addAccount.dataSource

import ru.crazerr.cashtracker.core.database.account.AccountDao
import ru.crazerr.cashtracker.core.database.account.AccountEntity
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account

internal class AddAccountLocalDataSource(
    private val accountDao: AccountDao,
) {
    suspend fun addAccount(account: Account): Result<Unit> {
        return try {
            accountDao.insert(
                accountEntity = account.toAccountEntity()
            )
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}

internal fun Account.toAccountEntity() =
    AccountEntity(
        id = id,
        name = name,
        balance = balance,
        currency = currency
    )