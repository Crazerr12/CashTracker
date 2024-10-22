package ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository

import ru.crazerr.cashtracker.feature.account.domain.api.model.Account

interface AccountsRepository {
    suspend fun getAccounts(): Result<List<Account>>
}
