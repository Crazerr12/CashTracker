package ru.crazerr.cashtracker.feature.budget.domain.repository

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

interface CategoriesRepository {
    suspend fun getAllCategories(): Result<List<Category>>
}
