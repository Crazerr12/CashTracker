package ru.crazerr.cashtracker.feature.category.data.addCategory.dataSource

import ru.crazerr.cashtracker.core.database.category.CategoryDao
import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

internal class AddCategoryLocalDataSource(
    private val categoryDao: CategoryDao,
) {
    suspend fun addCategory(category: Category): Result<Unit> {
        return try {
            categoryDao.insert(
                categoryEntity = arrayOf(
                    category.toCategoryEntity()
                )
            )
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}

internal fun Category.toCategoryEntity() =
    CategoryEntity(
        id = id,
        name = name
    )