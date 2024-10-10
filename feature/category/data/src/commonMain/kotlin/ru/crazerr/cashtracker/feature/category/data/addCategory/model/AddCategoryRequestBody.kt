package ru.crazerr.cashtracker.feature.category.data.addCategory.model

import ru.crazerr.cashtracker.core.database.category.CategoryEntity

internal class AddCategoryRequestBody(
    val name: String,
)

internal fun AddCategoryRequestBody.toCategoryEntity() =
    CategoryEntity(
        name = name,
    )