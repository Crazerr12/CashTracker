package ru.crazerr.cashtracker.feature.transactions.presentation.transactions.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getCategories.GetCategoriesResult
import ru.crazerr.cashtracker.feature.transactions.presentation.transactions.TransactionsComponent

internal class GetCategoriesResultHandler(
    private val result: GetCategoriesResult,
    private val delegate: TransactionsComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetCategoriesResult.Ok -> onOk(categories = result.categories)
            GetCategoriesResult.NetworkError -> onNetworkError()
            is GetCategoriesResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(categories: List<Category>) {
        delegate.reduceState {
            copy(
                categoriesFilter = categories.associateBy(
                    keySelector = { it },
                    valueTransform = { true }
                )
            )
        }
    }
}
