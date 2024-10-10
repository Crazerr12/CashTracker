package ru.crazerr.cashtracker.feature.account.data.accountDialog.dataSource

import ru.crazerr.cashtracker.core.database.account.AccountDao
import ru.crazerr.cashtracker.feature.account.data.accountDialog.model.AddAccountRequestBody
import ru.crazerr.cashtracker.feature.account.data.accountDialog.model.toAccountEntity

internal class AddAccountLocalDataSource(
    private val accountDao: AccountDao,
) {
    suspend fun addAccount(name: String, balance: Float, currency: String): Result<Long> {
        return try {
            val id = accountDao.insert(
                accountEntity = AddAccountRequestBody(
                    name = name,
                    balance = balance,
                    currency = currency,
                ).toAccountEntity()
            )
            Result.success(id)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
