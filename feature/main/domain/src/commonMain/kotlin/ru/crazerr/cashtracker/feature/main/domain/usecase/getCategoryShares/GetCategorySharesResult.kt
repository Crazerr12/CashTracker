package ru.crazerr.cashtracker.feature.main.domain.usecase.getCategoryShares

import ru.crazerr.cashtracker.feature.main.domain.model.CategoryShare

sealed interface GetCategorySharesResult {
    data class Ok(val categoryShares: List<CategoryShare>) : GetCategorySharesResult
    data object NetworkError : GetCategorySharesResult
    data class UnknownError(val throwable: Throwable) : GetCategorySharesResult
}
