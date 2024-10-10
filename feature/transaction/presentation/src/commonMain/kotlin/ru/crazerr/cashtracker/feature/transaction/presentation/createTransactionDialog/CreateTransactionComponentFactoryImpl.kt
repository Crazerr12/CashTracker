package ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentFactory
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentFactory
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.addTransaction.AddTransactionUseCase
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.getCategories.GetCategoriesUseCase
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentAction
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentFactory

internal class CreateTransactionComponentFactoryImpl(
    private val addTransactionUseCase: AddTransactionUseCase,
    private val getAccountsUseCase: GetAccountsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val createAccountComponentFactory: CreateAccountComponentFactory,
    private val createCategoryComponentFactory: CreateCategoryComponentFactory,
) : CreateTransactionComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (CreateTransactionComponentAction) -> Unit,
    ) = CreateTransactionComponentImpl(
        componentContext = componentContext,
        onAction = onAction,
        dependencies = CreateTransactionDependencies(
            addTransactionUseCase = addTransactionUseCase,
            getAccountsUseCase = getAccountsUseCase,
            getCategoriesUseCase = getCategoriesUseCase,
            createAccountComponentFactory = createAccountComponentFactory,
            createCategoryComponentFactory = createCategoryComponentFactory,
        )
    )
}
