package ru.crazerr.cashtracker.feature.budgets.presentation.budgets

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.core.utils.presentation.ComponentFactory
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponentFactory
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.deletBudgetById.DeleteBudgetUseCase
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.getBudgets.GetBudgetsUseCase

interface BudgetsComponentFactory : ComponentFactory<BudgetsComponent, BudgetsComponentAction>

internal class BudgetsComponentFactoryImpl(
    private val getBudgetsUseCase: GetBudgetsUseCase,
    private val newBudgetComponentFactory: NewBudgetComponentFactory,
    private val deleteBudgetUseCase: DeleteBudgetUseCase,
) : BudgetsComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (BudgetsComponentAction) -> Unit,
    ) = BudgetsComponentImpl(
        componentContext = componentContext,
        onAction = onAction,
        dependencies = BudgetsDependencies(
            getBudgetsUseCase = getBudgetsUseCase,
            newBudgetComponentFactory = newBudgetComponentFactory,
            deleteBudgetUseCase = deleteBudgetUseCase,
        )
    )
}
