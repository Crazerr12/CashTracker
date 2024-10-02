package ru.crazerr.cashtracker.feature.account.data

import ru.crazerr.cashtracker.feature.account.data.addAccount.dataSource.AddAccountLocalDataSource
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.account.domain.repository.AccountRepository

internal class AccountRepositoryImpl(
    private val addAccountLocalDataSource: AddAccountLocalDataSource,
) : AccountRepository {
    override suspend fun addAccount(account: Account): Result<Unit> {
        return addAccountLocalDataSource.addAccount(account = account)
    }
}