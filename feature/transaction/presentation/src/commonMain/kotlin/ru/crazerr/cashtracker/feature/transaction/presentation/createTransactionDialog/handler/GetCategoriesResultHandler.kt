package ru.crazerr.cashtracker.feature.transaction.presentation.createTransactionDialog.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.transaction.domain.usecase.getCategories.GetCategoriesResult
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponent

internal class GetCategoriesResultHandler(
    private val result: GetCategoriesResult,
    private val delegate: CreateTransactionComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetCategoriesResult.Ok -> onOk(categories = result.categories)
            GetCategoriesResult.NetworkError -> onNetworkError()
            is GetCategoriesResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(categories: List<Category>) {
        if (categories.isNotEmpty()) {
            delegate.reduceState {
                copy(categories = categories, selectedCategory = categories[0])
            }
        }
    }
}