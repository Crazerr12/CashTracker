package ru.crazerr.cashtracker.feature.budgets.presentation.budgetsStory

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.core.utils.presentation.ComponentFactory
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsComponentFactory

interface BudgetsStoryComponentFactory :
    ComponentFactory<BudgetsStoryComponent, BudgetsStoryComponentAction>

class BudgetsStoryComponentFactoryImpl(
    private val budgetsComponentFactory: BudgetsComponentFactory,
) : BudgetsStoryComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (BudgetsStoryComponentAction) -> Unit,
    ) = BudgetsStoryComponent(
        componentContext = componentContext,
        onAction = onAction,
        factories = BudgetsStoryComponentFactories(
            budgetsComponentFactory = budgetsComponentFactory
        )
    )
}
