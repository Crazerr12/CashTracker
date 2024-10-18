package ru.crazerr.cashtracker.feature.account.data

import org.koin.dsl.module
import ru.crazerr.cashtracker.core.database.AppDatabase
import ru.crazerr.cashtracker.feature.account.data.accountDialog.addAccount.dataSource.AddAccountLocalDataSource
import ru.crazerr.cashtracker.feature.account.data.accountDialog.getCurrencies.dataSource.GetCurrenciesLocalDataSource
import ru.crazerr.cashtracker.feature.account.domain.repository.AccountRepository
import ru.crazerr.cashtracker.feature.account.domain.repository.CurrencyRepository

val accountDataModule = module {
    single<AccountRepository> {
        AccountRepositoryImpl(
            addAccountLocalDataSource = AddAccountLocalDataSource(accountDao = get<AppDatabase>().accountDao())
        )
    }

    single<CurrencyRepository> {
        CurrencyRepositoryImpl(
            getCurrenciesLocalDataSource = GetCurrenciesLocalDataSource(currencyDao = get<AppDatabase>().currencyDao())
        )
    }
}
