package ru.crazerr.cashtracker.feature.transactions.presentation

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.transactions.data.dataTransactionsModule
import ru.crazerr.cashtracker.feature.transactions.domain.domainTransactionsModule
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsComponentFactory
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsComponentFactoryImpl
import ru.crazerr.cashtracker.feature.transactions.presentation.transactionsStory.TransactionsStoryComponentFactory
import ru.crazerr.cashtracker.feature.transactions.presentation.transactionsStory.TransactionsStoryComponentFactoryImpl

val transactionsPresentationModule = module {
    single<TransactionsStoryComponentFactory> {
        TransactionsStoryComponentFactoryImpl(
            transactionsComponentFactory = get()
        )
    }
    single<TransactionsComponentFactory> {
        TransactionsComponentFactoryImpl()
    }
    includes(dataTransactionsModule, domainTransactionsModule)
}