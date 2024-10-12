package ru.crazerr.cashtracker.feature.transaction.domain.repository

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

interface CategoryRepository {
    suspend fun getCategories(): Result<List<Category>>
}
