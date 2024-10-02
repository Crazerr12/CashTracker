package ru.crazerr.cashtracker.feature.account.data

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.account.data.addAccount.dataSource.AddAccountLocalDataSource
import ru.crazerr.cashtracker.feature.account.domain.repository.AccountRepository

val accountDataModule = module {
    single<AccountRepository> {
        AccountRepositoryImpl(
            addAccountLocalDataSource = AddAccountLocalDataSource(get())
        )
    }
}