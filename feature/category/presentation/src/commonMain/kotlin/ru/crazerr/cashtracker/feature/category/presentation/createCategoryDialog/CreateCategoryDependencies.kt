package ru.crazerr.cashtracker.feature.category.presentation.createCategoryDialog

import ru.crazerr.cashtracker.feature.category.domain.usecase.addCategory.AddCategoryUseCase

internal data class CreateCategoryDependencies(
    val addCategoryUseCase: AddCategoryUseCase,
)
