package ru.crazerr.cashtracker.feature.main.data

import org.koin.dsl.module
import ru.crazerr.cashtracker.core.database.AppDatabase
import ru.crazerr.cashtracker.feature.main.data.accounts.AccountsRepositoryImpl
import ru.crazerr.cashtracker.feature.main.data.categories.CategoryRepositoryImpl
import ru.crazerr.cashtracker.feature.main.data.transactions.TransactionsRepositoryImpl
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.AccountsDataSource
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.CategoriesDataSource
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.TransactionsLocalDataSource
import ru.crazerr.cashtracker.feature.main.domain.repository.AccountsRepository
import ru.crazerr.cashtracker.feature.main.domain.repository.CategoryRepository
import ru.crazerr.cashtracker.feature.main.domain.repository.TransactionsRepository

val mainDataModule = module {
    includes()
    factory<MainRepositoryImpl> {
        MainRepositoryImpl(get<AppDatabase>().categoryDao())
    }
    single<CategoryRepository> {
        CategoryRepositoryImpl(
            categoriesDataSource = CategoriesDataSource(get<AppDatabase>().categoryDao())
        )
    }
    single<AccountsRepository> {
        AccountsRepositoryImpl(
            accountsDataSource = AccountsDataSource(get<AppDatabase>().accountDao())
        )
    }
    single<TransactionsRepository> {
        TransactionsRepositoryImpl(
            transactionsLocalDataSource = TransactionsLocalDataSource(get<AppDatabase>().transactionDao()),
        )
    }
}
