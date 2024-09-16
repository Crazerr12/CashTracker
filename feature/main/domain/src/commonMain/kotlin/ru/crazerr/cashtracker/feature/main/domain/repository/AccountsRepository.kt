package ru.crazerr.cashtracker.feature.main.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.feature.main.domain.model.Account

interface AccountsRepository {

    suspend fun getAllAccounts(): Result<List<Account>>
}