package ru.crazerr.cashtracker.feature.account.data.accountDialog.getCurrencies.dataSource

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.currency.CurrencyDao
import ru.crazerr.cashtracker.core.database.currency.CurrencyEntity
import ru.crazerr.cashtracker.currency.domain.api.model.Currency

internal class GetCurrenciesLocalDataSource(
    private val currencyDao: CurrencyDao,
) {
    suspend fun getCurrencies(): Result<List<Currency>> {
        return try {
//            currencyDao.insert(
//                CurrencyEntity(
//                    name = "US Dollar",
//                    code = "USD",
//                    symbolNative = "$",
//                    symbol = "$"
//                )
//            )
//            currencyDao.insert(
//                CurrencyEntity(
//                    name = "Российский рубль",
//                    code = "RUB",
//                    symbolNative = "₽",
//                    symbol = "₽"
//                )
//            )
            Result.success(currencyDao.getAll().first().toCurrencies())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}

internal fun List<CurrencyEntity>.toCurrencies() =
    map {
        Currency(
            id = it.id,
            name = it.name,
            code = it.code,
            symbolNative = it.symbolNative,
            symbol = it.symbol
        )
    }
