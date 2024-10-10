package ru.crazerr.cashtracker.feature.transaction.domain.usecase.getCategories

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

sealed interface GetCategoriesResult {
    data class Ok(val categories: List<Category>) : GetCategoriesResult
    data object NetworkError : GetCategoriesResult
    data class UnknownError(val throwable: Throwable) : GetCategoriesResult
}