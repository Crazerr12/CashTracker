package ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

interface CategoriesRepository {
    suspend fun getCategories(): Result<List<Category>>
}
