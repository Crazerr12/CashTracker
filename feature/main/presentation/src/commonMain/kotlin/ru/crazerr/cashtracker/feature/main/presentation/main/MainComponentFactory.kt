package ru.crazerr.cashtracker.feature.main.presentation.main

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.core.utils.presentation.ComponentFactory
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentFactory
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentFactory
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCase
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentFactory

interface MainComponentFactory : ComponentFactory<MainComponent, MainComponentAction>

class MainComponentFactoryImpl(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val createTransactionComponentFactory: CreateTransactionComponentFactory,
    private val createAccountComponentFactory: CreateAccountComponentFactory,
    private val createCategoryComponentFactory: CreateCategoryComponentFactory,
) : MainComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (MainComponentAction) -> Unit,
    ): MainComponent {
        return MainComponent(
            componentContext = componentContext,
            onAction = onAction,
            dependencies = MainDependencies(
                getTransactionsUseCase = getTransactionsUseCase,
                getAccountsUseCase = getAccountsUseCase,
                createTransactionComponentFactory = createTransactionComponentFactory,
                createCategoryComponentFactory = createCategoryComponentFactory,
                createAccountComponentFactory = createAccountComponentFactory,
            )
        )
    }
}
