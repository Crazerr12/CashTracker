package ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

sealed interface CreateCategoryComponentAction {
    data object Canceled : CreateCategoryComponentAction
    data class CategoryCreated(val category: Category) : CreateCategoryComponentAction
}