package ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog

sealed interface CreateCategoryViewAction {
    data object CancelClick : CreateCategoryViewAction
    data class UpdateName(val name: String) : CreateCategoryViewAction
    data object SaveClick : CreateCategoryViewAction
}