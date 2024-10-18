package ru.crazerr.cashtracker.feature.main.domain.repository

import ru.crazerr.cashtracker.feature.account.domain.api.model.Account

interface AccountsRepository {

    suspend fun getAllAccounts(): Result<List<Account>>
}
