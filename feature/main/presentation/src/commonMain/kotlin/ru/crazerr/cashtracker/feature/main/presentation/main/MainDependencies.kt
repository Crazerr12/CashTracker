package ru.crazerr.cashtracker.feature.main.presentation.main

import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentFactory
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentFactory
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCase
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentFactory

class MainDependencies(
    val getTransactionsUseCase: GetTransactionsUseCase,
    val getAccountsUseCase: GetAccountsUseCase,
    val createTransactionComponentFactory: CreateTransactionComponentFactory,
    val createCategoryComponentFactory: CreateCategoryComponentFactory,
    val createAccountComponentFactory: CreateAccountComponentFactory,
)