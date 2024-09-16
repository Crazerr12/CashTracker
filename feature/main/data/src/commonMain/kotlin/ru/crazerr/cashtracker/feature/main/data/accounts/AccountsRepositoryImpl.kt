package ru.crazerr.cashtracker.feature.main.data.accounts

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ru.crazerr.cashtracker.core.database.account.AccountEntity
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.AccountsDataSource
import ru.crazerr.cashtracker.feature.main.domain.model.Account
import ru.crazerr.cashtracker.feature.main.domain.repository.AccountsRepository

internal class AccountsRepositoryImpl(
    private val accountsDataSource: AccountsDataSource,
) : AccountsRepository {
    override suspend fun getAllAccounts(): Result<List<Account>> {
        return try {
            val flow = accountsDataSource.getAccounts()
            val accountEntities = flow.first()
            val accounts = accountEntities.toAccounts()
            Result.success(accounts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

fun List<AccountEntity>.toAccounts() =
    map { accountEntity ->
        Account(
            id = accountEntity.id,
            name = accountEntity.name,
            balance = accountEntity.balance,
            currency = accountEntity.currency
        )
    }