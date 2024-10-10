package ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog

data class CreateCategoryState(
    val name: String,
    val buttonLoading: Boolean,
)

internal val InitialCreateCategoryState = CreateCategoryState(
    name = "",
    buttonLoading = false,
)
