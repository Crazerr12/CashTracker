package ru.crazerr.cashtracker.feature.account.domain.repository

interface AccountRepository {
    suspend fun addAccount(name: String, balance: Float, currency: String): Result<Long>
}