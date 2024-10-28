package ru.crazerr.cashtracker.feature.budgets.presentation.budgets

import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponentFactory
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.deletBudgetById.DeleteBudgetUseCase
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.getBudgets.GetBudgetsUseCase

class BudgetsDependencies(
    val getBudgetsUseCase: GetBudgetsUseCase,
    val newBudgetComponentFactory: NewBudgetComponentFactory,
    val deleteBudgetUseCase: DeleteBudgetUseCase,
)
