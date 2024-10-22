package ru.crazerr.cashtracker.feature.transactions.data

import org.koin.dsl.module
import ru.crazerr.cashtracker.core.database.AppDatabase
import ru.crazerr.cashtracker.feature.transactions.data.transactions.getAccounts.dataSource.GetAccountsLocalDataSource
import ru.crazerr.cashtracker.feature.transactions.data.transactions.getCategories.dataSource.GetCategoriesLocalDataSource
import ru.crazerr.cashtracker.feature.transactions.data.transactions.getSummaryInfoAboutTransactions.dataSource.GetTransactionsSummaryInfoLocalDataSource
import ru.crazerr.cashtracker.feature.transactions.data.transactions.getTransactions.dataSource.GetTransactionsLocalDataSource
import ru.crazerr.cashtracker.feature.transactions.data.transactions.repository.AccountsRepositoryImpl
import ru.crazerr.cashtracker.feature.transactions.data.transactions.repository.CategoriesRepositoryImpl
import ru.crazerr.cashtracker.feature.transactions.data.transactions.repository.TransactionsRepositoryImpl
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.AccountsRepository
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.CategoriesRepository
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.TransactionsRepository

val dataTransactionsModule = module {
    single<AccountsRepository> {
        AccountsRepositoryImpl(
            getAccountsLocalDataSource = GetAccountsLocalDataSource(
                accountDao = get<AppDatabase>().accountDao()
            )
        )
    }

    single<CategoriesRepository> {
        CategoriesRepositoryImpl(
            getCategoriesLocalDataSource = GetCategoriesLocalDataSource(
                categoryDao = get<AppDatabase>().categoryDao()
            )
        )
    }

    single<TransactionsRepository> {
        TransactionsRepositoryImpl(
            getTransactionsLocalDataSource = GetTransactionsLocalDataSource(
                transactionDao = get<AppDatabase>().transactionDao()
            ),
            getTransactionsSummaryInfoLocalDataSource = GetTransactionsSummaryInfoLocalDataSource(
                transactionDao = get<AppDatabase>().transactionDao()
            )
        )
    }
}
