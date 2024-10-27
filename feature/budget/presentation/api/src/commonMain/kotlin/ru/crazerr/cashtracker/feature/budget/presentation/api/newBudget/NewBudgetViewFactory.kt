package ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface NewBudgetViewFactory {
    @Composable
    fun create(modifier: Modifier, component: NewBudgetComponent)
}
