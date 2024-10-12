package ru.crazerr.cashtracker.feature.main.domain.repository

import ru.crazerr.cashtracker.feature.main.domain.model.Account

interface AccountsRepository {

    suspend fun getAllAccounts(): Result<List<Account>>
}
