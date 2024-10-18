package ru.crazerr.cashtracker.feature.main.presentation.main.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.main.domain.model.CategoryShare
import ru.crazerr.cashtracker.feature.main.domain.usecase.getCategoryShares.GetCategorySharesResult
import ru.crazerr.cashtracker.feature.main.presentation.main.MainComponent

internal class GetCategorySharesResultHandler(
    private val result: GetCategorySharesResult,
    private val delegate: MainComponent,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is GetCategorySharesResult.Ok -> onOk(categoryShares = result.categoryShares)
            GetCategorySharesResult.NetworkError -> onNetworkError()
            is GetCategorySharesResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(categoryShares: List<CategoryShare>) {
        delegate.reduceState { copy(categoryShares = categoryShares) }
    }
}
