package ru.crazerr.cashtracker.feature.account.data

import ru.crazerr.cashtracker.feature.account.data.accountDialog.addAccount.dataSource.AddAccountLocalDataSource
import ru.crazerr.cashtracker.feature.account.domain.repository.AccountRepository

internal class AccountRepositoryImpl(
    private val addAccountLocalDataSource: AddAccountLocalDataSource,
) : AccountRepository {
    override suspend fun addAccount(
        name: String,
        balance: Float,
        currencyId: Long,
    ): Result<Long> {
        return addAccountLocalDataSource.addAccount(
            name = name,
            balance = balance,
            currencyId = currencyId
        )
    }
}
