package ru.crazerr.cashtracker.feature.budgets.presentation.budgets.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.budget.domain.api.model.BudgetCategory
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.getBudgets.GetBudgetsResult
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsComponent

internal class GetBudgetsResultHandler(
    private val result: GetBudgetsResult,
    private val delegate: BudgetsComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetBudgetsResult.Ok -> onOk(budgets = result.budgets)
            GetBudgetsResult.NetworkError -> onNetworkError()
            is GetBudgetsResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(budgets: List<BudgetCategory>) {
        delegate.reduceState { copy(budgets = budgets) }
    }
}
