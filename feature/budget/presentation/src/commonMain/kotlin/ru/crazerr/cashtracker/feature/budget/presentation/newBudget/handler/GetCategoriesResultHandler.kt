package ru.crazerr.cashtracker.feature.budget.presentation.newBudget.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.budget.domain.usecase.getCategories.GetCategoriesResult
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponent
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

internal class GetCategoriesResultHandler(
    private val result: GetCategoriesResult,
    private val delegate: NewBudgetComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetCategoriesResult.Ok -> onOk(categories = result.categories)
            GetCategoriesResult.NetworkError -> onNetworkError()
            is GetCategoriesResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(categories: List<Category>) {
        delegate.reduceState { copy(categories = categories) }
    }
}
