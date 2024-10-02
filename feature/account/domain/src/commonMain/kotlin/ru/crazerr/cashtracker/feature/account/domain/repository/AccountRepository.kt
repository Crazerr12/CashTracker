package ru.crazerr.cashtracker.feature.account.domain.repository

import ru.crazerr.cashtracker.feature.account.domain.api.model.Account

interface AccountRepository {
    suspend fun addAccount(account: Account): Result<Unit>
}