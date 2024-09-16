package ru.crazerr.cashtracker.feature.main.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.feature.main.domain.model.Category

interface CategoryRepository {

    suspend fun getCategories(): Result<List<Category>>
}