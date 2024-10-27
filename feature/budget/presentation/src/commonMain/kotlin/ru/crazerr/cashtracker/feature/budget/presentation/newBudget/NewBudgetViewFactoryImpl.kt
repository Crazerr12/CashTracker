package ru.crazerr.cashtracker.feature.budget.presentation.newBudget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponent
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetViewFactory
import ru.crazerr.cashtracker.feature.budget.presentation.newBudget.ui.NewBudgetView

internal class NewBudgetViewFactoryImpl : NewBudgetViewFactory {
    @Composable
    override fun create(modifier: Modifier, component: NewBudgetComponent) {
        NewBudgetView(modifier = modifier, component = component)
    }
}
