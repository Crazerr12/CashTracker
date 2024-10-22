package ru.crazerr.cashtracker.feature.transactions.presentation.transactions

import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentFactory
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getCategories.GetCategoriesUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getSummary.GetSummaryInfoUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getTransactions.GetTransactionsUseCase

class TransactionsDependencies(
    val getTransactionsUseCase: GetTransactionsUseCase,
    val createTransactionsComponentFactory: CreateTransactionComponentFactory,
    val getAccountsUseCase: GetAccountsUseCase,
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getSummaryInfoUseCase: GetSummaryInfoUseCase,
)
