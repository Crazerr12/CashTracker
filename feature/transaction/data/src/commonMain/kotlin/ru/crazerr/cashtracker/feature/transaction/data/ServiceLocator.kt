package ru.crazerr.cashtracker.feature.transaction.data

import org.koin.dsl.module
import ru.crazerr.cashtracker.core.database.AppDatabase
import ru.crazerr.cashtracker.feature.transaction.data.account.AccountRepositoryImpl
import ru.crazerr.cashtracker.feature.transaction.data.account.getAccounts.dataSource.GetAccountsLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.data.category.CategoryRepositoryImpl
import ru.crazerr.cashtracker.feature.transaction.data.category.getCategories.dataSource.GetCategoriesLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.data.transaction.TransactionRepositoryImpl
import ru.crazerr.cashtracker.feature.transaction.data.transaction.addTransaction.dataSource.AddTransactionLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.domain.repository.AccountRepository
import ru.crazerr.cashtracker.feature.transaction.domain.repository.CategoryRepository
import ru.crazerr.cashtracker.feature.transaction.domain.repository.TransactionRepository

val transactionDataModule = module {
    single<TransactionRepository> {
        TransactionRepositoryImpl(
            addTransactionLocalDataSource = AddTransactionLocalDataSource(
                transactionDao = get<AppDatabase>().transactionDao()
            )
        )
    }

    single<AccountRepository> {
        AccountRepositoryImpl(
            getAccountsLocalDataSource = GetAccountsLocalDataSource(accountDao = get<AppDatabase>().accountDao())
        )
    }

    single<CategoryRepository> {
        CategoryRepositoryImpl(
            getCategoriesLocalDataSource = GetCategoriesLocalDataSource(categoryDao = get<AppDatabase>().categoryDao())
        )
    }
}
