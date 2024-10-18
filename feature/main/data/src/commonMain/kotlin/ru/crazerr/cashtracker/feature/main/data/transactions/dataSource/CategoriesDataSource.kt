package ru.crazerr.cashtracker.feature.main.data.transactions.dataSource

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.category.CategoryDao
import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

internal class CategoriesDataSource(
    private val categoryDao: CategoryDao,
) {
    suspend fun getCategories(): Result<List<Category>> {
        return try {
            val categoryEntities = categoryDao.getAll().first()
            Result.success(categoryEntities.toCategories())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}

fun List<CategoryEntity>.toCategories() =
    map {
        Category(
            id = it.id,
            name = it.name,
            color = it.color,
            iconId = it.iconId,
        )
    }
