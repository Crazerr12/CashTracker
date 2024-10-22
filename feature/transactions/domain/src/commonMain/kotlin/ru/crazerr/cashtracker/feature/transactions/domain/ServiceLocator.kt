package ru.crazerr.cashtracker.feature.transactions.domain

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getAccounts.GetAccountUseCaseImpl
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getCategories.GetCategoriesUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getCategories.GetCategoriesUseCaseImpl
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getSummary.GetSummaryInfoUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getSummary.GetSummaryInfoUseCaseImpl
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getTransactions.GetTransactionsUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getTransactions.GetTransactionsUseCaseImpl

val domainTransactionsModule = module {
    factory<GetTransactionsUseCase> {
        GetTransactionsUseCaseImpl(transactionsRepository = get())
    }

    factory<GetCategoriesUseCase> {
        GetCategoriesUseCaseImpl(categoriesRepository = get())
    }

    factory<GetAccountsUseCase> {
        GetAccountUseCaseImpl(accountsRepository = get())
    }

    factory<GetSummaryInfoUseCase> {
        GetSummaryInfoUseCaseImpl(transactionsRepository = get())
    }
}
