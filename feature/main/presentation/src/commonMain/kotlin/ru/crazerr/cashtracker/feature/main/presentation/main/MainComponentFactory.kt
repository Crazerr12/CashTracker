package ru.crazerr.cashtracker.feature.main.presentation.main

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.core.utils.presentation.ComponentFactory
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCase

interface MainComponentFactory : ComponentFactory<MainComponent, MainComponentAction>

class MainComponentFactoryImpl(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
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
            )
        )
    }
}