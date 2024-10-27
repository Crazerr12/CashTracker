package ru.crazerr.cashtracker.feature.budget.presentation.newBudget.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.budget.domain.usecase.addBudgetCategory.AddBudgetCategoryResult

internal class AddBudgetCategoryResultHandler(
    private val result: AddBudgetCategoryResult,
    private val onAction: () -> Unit
) : ResultHandler() {
    override fun handle() {
        when (result) {
            AddBudgetCategoryResult.Ok -> onOk()
            AddBudgetCategoryResult.NetworkError -> onNetworkError()
            is AddBudgetCategoryResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk() {
        onAction()
    }
}
