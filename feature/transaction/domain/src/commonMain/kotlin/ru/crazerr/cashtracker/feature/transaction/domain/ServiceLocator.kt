package ru.crazerr.cashtracker.feature.transaction.domain

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.addTransaction.AddTransactionUseCase
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.addTransaction.AddTransactionUseCaseImpl
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.getAccounts.GetAccountsUseCaseImpl
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.getCategories.GetCategoriesUseCase
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.getCategories.GetCategoriesUseCaseImpl

val transactionDomainModule = module {
    factory<AddTransactionUseCase> {
        AddTransactionUseCaseImpl(
            transactionRepository = get()
        )
    }

    factory<GetCategoriesUseCase> {
        GetCategoriesUseCaseImpl(
            categoryRepository = get()
        )
    }

    factory<GetAccountsUseCase> {
        GetAccountsUseCaseImpl(
            accountRepository = get()
        )
    }
}