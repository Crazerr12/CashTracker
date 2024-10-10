package ru.crazerr.cashtracker.feature.category.data.addCategory.dataSource

import ru.crazerr.cashtracker.core.database.category.CategoryDao
import ru.crazerr.cashtracker.feature.category.data.addCategory.model.AddCategoryRequestBody
import ru.crazerr.cashtracker.feature.category.data.addCategory.model.toCategoryEntity

internal class AddCategoryLocalDataSource(
    private val categoryDao: CategoryDao,
) {
    suspend fun addCategory(name: String): Result<Long> {
        return try {
            val id = categoryDao.insert(
                categoryEntity = AddCategoryRequestBody(name = name).toCategoryEntity()
            )
            Result.success(id)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}