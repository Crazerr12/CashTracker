package ru.crazerr.cashtracker.feature.budgets.presentation.budgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.ui.BudgetsView

interface BudgetsViewFactory {
    @Composable
    fun create(modifier: Modifier, component: BudgetsComponent)
}

internal class BudgetsViewFactoryImpl : BudgetsViewFactory {
    @Composable
    override fun create(modifier: Modifier, component: BudgetsComponent) {
        BudgetsView(modifier = modifier, component = component)
    }
}
