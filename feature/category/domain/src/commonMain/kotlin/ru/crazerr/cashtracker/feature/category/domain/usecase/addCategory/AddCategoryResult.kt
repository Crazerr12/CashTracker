package ru.crazerr.cashtracker.feature.category.domain.usecase.addCategory

sealed interface AddCategoryResult {
    data class Ok(val id: Long) : AddCategoryResult
    data object NetworkError : AddCategoryResult
    data class UnknownError(val throwable: Throwable) : AddCategoryResult
}