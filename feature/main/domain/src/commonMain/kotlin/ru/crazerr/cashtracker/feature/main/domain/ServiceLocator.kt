package ru.crazerr.cashtracker.feature.main.domain

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsUseCaseImpl
import ru.crazerr.cashtracker.feature.main.domain.usecase.getCategoryShares.GetCategorySharesUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getCategoryShares.GetCategorySharesUseCaseImpl
import ru.crazerr.cashtracker.feature.main.domain.usecase.getExpensesAndIncomeByMonth.GetExpensesAndIncomeByMonthUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getExpensesAndIncomeByMonth.GetExpensesAndIncomeByMonthUseCaseImpl
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCaseImpl

val mainDomainModule = module {
    factory<GetTransactionsUseCase> { GetTransactionsUseCaseImpl(transactionsRepository = get()) }
    factory<GetAccountsUseCase> { GetAccountsUseCaseImpl(accountsRepository = get()) }
    factory<GetCategorySharesUseCase> { GetCategorySharesUseCaseImpl(transactionsRepository = get()) }
    factory<GetExpensesAndIncomeByMonthUseCase> {
        GetExpensesAndIncomeByMonthUseCaseImpl(
            transactionsRepository = get()
        )
    }
}
