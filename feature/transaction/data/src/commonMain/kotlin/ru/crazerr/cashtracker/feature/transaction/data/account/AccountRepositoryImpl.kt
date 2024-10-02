package ru.crazerr.cashtracker.feature.transaction.data.account

import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.transaction.data.account.addAccount.dataSource.AddAccountLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.data.account.getAccounts.dataSource.GetAccountsLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.domain.repository.AccountRepository

internal class AccountRepositoryImpl(
    private val addAccountLocalDataSource: AddAccountLocalDataSource,
    private val getAccountsLocalDataSource: GetAccountsLocalDataSource,
) : AccountRepository {
    override suspend fun getAccounts(): Result<List<Account>> {
        return getAccountsLocalDataSource.getAccounts()
    }

    override suspend fun addAccount(account: Account): Result<Unit> {
        return addAccountLocalDataSource.addAccount(account = account)
    }
}