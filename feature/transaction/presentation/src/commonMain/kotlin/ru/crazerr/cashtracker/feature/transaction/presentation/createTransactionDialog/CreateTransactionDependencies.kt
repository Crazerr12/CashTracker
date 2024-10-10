package ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog

import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentFactory
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentFactory
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.addTransaction.AddTransactionUseCase
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.getCategories.GetCategoriesUseCase

internal data class CreateTransactionDependencies(
    val addTransactionUseCase: AddTransactionUseCase,
    val getAccountsUseCase: GetAccountsUseCase,
    val getCategoriesUseCase: GetCategoriesUseCase,
    val createCategoryComponentFactory: CreateCategoryComponentFactory,
    val createAccountComponentFactory: CreateAccountComponentFactory,
)