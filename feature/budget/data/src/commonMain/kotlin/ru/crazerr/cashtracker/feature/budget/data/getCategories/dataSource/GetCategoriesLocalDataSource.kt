package ru.crazerr.cashtracker.feature.budget.data.getCategories.dataSource

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.category.CategoryDao
import ru.crazerr.cashtracker.core.database.category.CategoryEntity

internal class GetCategoriesLocalDataSource(
    private val categoryDao: CategoryDao,
) {
    suspend fun getAllCategories(): Result<List<CategoryEntity>> {
        return try {
            Result.success(categoryDao.getAll().first())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
