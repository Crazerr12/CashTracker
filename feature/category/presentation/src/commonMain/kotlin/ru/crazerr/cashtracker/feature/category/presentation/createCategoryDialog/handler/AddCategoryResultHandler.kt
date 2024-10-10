package ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog.handler

import ru.crazerr.cashtracker.core.utils.ResultHandler
import ru.crazerr.cashtracker.feature.category.domain.usecase.addCategory.AddCategoryResult

internal class AddCategoryResultHandler(
    private val result: AddCategoryResult,
    private val onAction: (Long) -> Unit,
) : ResultHandler() {
    override fun handle() {
        when (result) {
            is AddCategoryResult.Ok -> onOk(id = result.id)
            AddCategoryResult.NetworkError -> onNetworkError()
            is AddCategoryResult.UnknownError -> onUnknownError(t = result.throwable)
        }
    }

    private fun onOk(id: Long) {
        onAction(id)
    }
}