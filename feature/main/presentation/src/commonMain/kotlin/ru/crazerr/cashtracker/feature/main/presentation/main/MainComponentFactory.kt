package ru.crazerr.cashtracker.feature.main.presentation.main

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.core.utils.presentation.ComponentFactory
import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentFactory
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponentFactory
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getCategoryShares.GetCategorySharesUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getExpensesAndIncomeByMonth.GetExpensesAndIncomeByMonthUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCase
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponentFactory
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentFactory

interface MainComponentFactory : ComponentFactory<MainComponent, MainComponentAction>

@Suppress("LongParameterList")
class MainComponentFactoryImpl(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val getCategorySharesUseCase: GetCategorySharesUseCase,
    private val createTransactionComponentFactory: CreateTransactionComponentFactory,
    private val createAccountComponentFactory: CreateAccountComponentFactory,
    private val getExpensesAndIncomeByMonthUseCase: GetExpensesAndIncomeByMonthUseCase,
    private val newBudgetComponentFactory: NewBudgetComponentFactory,
    private val newGoalComponentFactory: NewGoalComponentFactory,
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
                getCategorySharesUseCase = getCategorySharesUseCase,
                createTransactionComponentFactory = createTransactionComponentFactory,
                createAccountComponentFactory = createAccountComponentFactory,
                getExpensesAndIncomeByMonthUseCase = getExpensesAndIncomeByMonthUseCase,
                newGoalComponentFactory = newGoalComponentFactory,
                newBudgetComponentFactory = newBudgetComponentFactory,
            )
        )
    }
}
