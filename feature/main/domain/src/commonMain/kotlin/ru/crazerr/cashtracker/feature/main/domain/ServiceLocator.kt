package ru.crazerr.cashtracker.feature.main.domain

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsUseCaseImpl
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCaseImpl

val mainDomainModule = module {
    factory<GetTransactionsUseCase> { GetTransactionsUseCaseImpl(get()) }
    factory<GetAccountsUseCase> { GetAccountsUseCaseImpl(get()) }
    factory<GetAccountsUseCase> { GetAccountsUseCaseImpl(get()) }
}
