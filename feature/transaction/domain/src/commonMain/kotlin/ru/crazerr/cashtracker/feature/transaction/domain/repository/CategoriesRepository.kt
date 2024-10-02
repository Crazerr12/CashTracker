package ru.crazerr.cashtracker.feature.transaction.domain.repository

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

sealed interface CategoriesRepository {
    suspend fun getCategories(): Result<List<Category>>

    suspend fun addCategory(category: Category): Result<Unit>
}