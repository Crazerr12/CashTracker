package ru.crazerr.cashtracker.feature.main.data.transactions.dataSource

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.account.AccountDao
import ru.crazerr.cashtracker.core.database.account.AccountEntity

internal class AccountsDataSource(
    private val accountDao: AccountDao,
) {

    fun getAccounts(): Flow<List<AccountEntity>> {
        return accountDao.getAll()
    }
}