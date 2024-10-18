package ru.crazerr.cashtracker.feature.account.data

import ru.crazerr.cashtracker.currency.domain.api.model.Currency
import ru.crazerr.cashtracker.feature.account.data.accountDialog.getCurrencies.dataSource.GetCurrenciesLocalDataSource
import ru.crazerr.cashtracker.feature.account.domain.repository.CurrencyRepository

internal class CurrencyRepositoryImpl(
    private val getCurrenciesLocalDataSource: GetCurrenciesLocalDataSource,
) : CurrencyRepository {
    override suspend fun getCurrencies(): Result<List<Currency>> {
        return getCurrenciesLocalDataSource.getCurrencies()
    }
}
