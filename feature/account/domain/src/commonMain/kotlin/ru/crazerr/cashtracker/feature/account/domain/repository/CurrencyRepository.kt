package ru.crazerr.cashtracker.feature.account.domain.repository

import ru.crazerr.cashtracker.currency.domain.api.model.Currency

interface CurrencyRepository {
    suspend fun getCurrencies(): Result<List<Currency>>
}
