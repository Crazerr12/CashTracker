package ru.crazerr.cashtracker.feature.account.data.accountDialog.addAccount.dataSource

import ru.crazerr.cashtracker.core.database.account.AccountDao
import ru.crazerr.cashtracker.feature.account.data.accountDialog.addAccount.model.AddAccountRequestBody
import ru.crazerr.cashtracker.feature.account.data.accountDialog.addAccount.model.toAccountEntity

internal class AddAccountLocalDataSource(
    private val accountDao: AccountDao,
) {
    suspend fun addAccount(name: String, balance: Float, currencyId: Long): Result<Long> {
        return try {
            val id = accountDao.insert(
                accountEntity = AddAccountRequestBody(
                    name = name,
                    balance = balance,
                    currencyId = currencyId,
                ).toAccountEntity()
            )
            Result.success(id)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
