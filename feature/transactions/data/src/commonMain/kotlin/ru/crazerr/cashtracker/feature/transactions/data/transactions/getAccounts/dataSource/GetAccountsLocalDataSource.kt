package ru.crazerr.cashtracker.feature.transactions.data.transactions.getAccounts.dataSource

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.account.AccountDao
import ru.crazerr.cashtracker.core.database.account.model.AccountWithCurrency

internal class GetAccountsLocalDataSource(
    private val accountDao: AccountDao,
) {
    fun getAccounts(): Flow<List<AccountWithCurrency>> {
        return accountDao.getAll()
    }
}
