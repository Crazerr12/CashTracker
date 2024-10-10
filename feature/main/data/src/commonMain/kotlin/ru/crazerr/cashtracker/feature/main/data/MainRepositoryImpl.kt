package ru.crazerr.cashtracker.feature.main.data

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.category.CategoryDao
import ru.crazerr.cashtracker.core.database.category.CategoryEntity

class MainRepositoryImpl(
    private val categoryDao: CategoryDao
) {
    fun getAllCategories(): Flow<List<CategoryEntity>> {
        return categoryDao.getAll()
    }

    suspend fun insertCategory(categoryEntity: CategoryEntity) {
        categoryDao.insert(categoryEntity)
    }
}