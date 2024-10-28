package ru.crazerr.cashtracker.feature.budgets.presentation.budgets

import ru.crazerr.cashtracker.feature.budget.domain.api.model.BudgetCategory

data class BudgetsState(
    val budgets: List<BudgetCategory>,
)

internal val InitialBudgetsState = BudgetsState(
    budgets = emptyList()
)
