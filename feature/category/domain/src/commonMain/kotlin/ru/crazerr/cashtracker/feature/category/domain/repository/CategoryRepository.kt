package ru.crazerr.cashtracker.feature.category.domain.repository

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

interface CategoryRepository {
    suspend fun addCategory(category: Category): Result<Unit>
}