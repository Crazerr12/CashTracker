package ru.crazerr.cashtracker.feature.transaction.presentation

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.transaction.data.transactionDataModule
import ru.crazerr.cashtracker.feature.transaction.domain.transactionDomainModule
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentFactory
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionViewFactory
import ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog.CreateTransactionComponentFactoryImpl
import ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog.CreateTransactionViewFactoryImpl

val transactionPresentationModule = module {
    factory<CreateTransactionComponentFactory> {
        CreateTransactionComponentFactoryImpl(
            addTransactionUseCase = get(),
            getAccountsUseCase = get(),
            getCategoriesUseCase = get(),
            createCategoryComponentFactory = get(),
            createAccountComponentFactory = get(),
        )
    }

    factory<CreateTransactionViewFactory> {
        CreateTransactionViewFactoryImpl()
    }

    includes(transactionDomainModule, transactionDataModule)
}