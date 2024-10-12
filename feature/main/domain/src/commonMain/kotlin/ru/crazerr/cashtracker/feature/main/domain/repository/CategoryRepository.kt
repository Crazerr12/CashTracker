package ru.crazerr.cashtracker.feature.main.domain.repository

import ru.crazerr.cashtracker.feature.main.domain.model.Category

interface CategoryRepository {

    suspend fun getCategories(): Result<List<Category>>
}
