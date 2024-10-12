package ru.crazerr.cashtracker.feature.transaction.domain.repository

import ru.crazerr.cashtracker.feature.account.domain.api.model.Account

interface AccountRepository {
    suspend fun getAccounts(): Result<List<Account>>
}
