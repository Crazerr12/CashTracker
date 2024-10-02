package ru.crazerr.cashtracker.feature.transaction.domain.usecase.addCategory

sealed interface AddCategoryResult {
    data object Ok : AddCategoryResult
    data class ValidationError(val throwable: Throwable): AddCategoryResult
    data object NetworkError : AddCategoryResult
    data class UnknownError(val throwable: Throwable) : AddCategoryResult
}