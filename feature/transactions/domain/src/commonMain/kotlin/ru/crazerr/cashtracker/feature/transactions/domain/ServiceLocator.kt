package ru.crazerr.cashtracker.feature.transactions.domain

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.addNewTransaction.AddNewTransactionUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.addNewTransaction.AddNewTransactionUseCaseImpl
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getTransactions.GetTransactionsUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getTransactions.GetTransactionsUseCaseImpl

val domainTransactionsModule = module {
    factory<GetTransactionsUseCase> {
        GetTransactionsUseCaseImpl(get())
    }
    factory<AddNewTransactionUseCase> {
        AddNewTransactionUseCaseImpl(get())
    }
}