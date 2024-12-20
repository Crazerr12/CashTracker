package ru.crazerr.cashtracker.feature.main.presentation.main

import ru.crazerr.cashtracker.feature.account.presentation.api.createAccountDialog.CreateAccountComponentFactory
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponentFactory
import ru.crazerr.cashtracker.feature.main.domain.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getCategoryShares.GetCategorySharesUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getExpensesAndIncomeByMonth.GetExpensesAndIncomeByMonthUseCase
import ru.crazerr.cashtracker.feature.main.domain.usecase.getTransactions.GetTransactionsUseCase
import ru.crazerr.cashtracker.feature.presentation.api.newGoal.NewGoalComponentFactory
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentFactory

@Suppress("LongParameterList")
class MainDependencies(
    val getTransactionsUseCase: GetTransactionsUseCase,
    val getAccountsUseCase: GetAccountsUseCase,
    val getCategorySharesUseCase: GetCategorySharesUseCase,
    val createTransactionComponentFactory: CreateTransactionComponentFactory,
    val createAccountComponentFactory: CreateAccountComponentFactory,
    val getExpensesAndIncomeByMonthUseCase: GetExpensesAndIncomeByMonthUseCase,
    val newBudgetComponentFactory: NewBudgetComponentFactory,
    val newGoalComponentFactory: NewGoalComponentFactory,
)
