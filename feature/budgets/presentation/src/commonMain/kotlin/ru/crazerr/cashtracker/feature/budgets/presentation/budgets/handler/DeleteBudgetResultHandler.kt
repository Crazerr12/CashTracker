package ru.crazerr.cashtracker.feature.budgets.presentation.budgets.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.budgets.domain.usecase.deletBudgetById.DeleteBudgetResult
import ru.crazerr.cashtracker.feature.budgets.presentation.budgets.BudgetsComponent

internal class DeleteBudgetResultHandler(
    private val result: DeleteBudgetResult,
    private val delegate: BudgetsComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is DeleteBudgetResult.Ok -> onOK(id = result.id)
            DeleteBudgetResult.NetworkError -> onNetworkError()
            is DeleteBudgetResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOK(id: Long) {
        delegate.reduceState { copy(budgets = budgets.filter { it.id != id }) }
    }
}
