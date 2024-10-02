package ru.crazerr.cashtracker.feature.category.data

import ru.crazerr.cashtracker.feature.category.data.addCategory.dataSource.AddCategoryLocalDataSource
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.category.domain.repository.CategoryRepository

internal class CategoryRepositoryImpl(
    private val addCategoryLocalDataSource: AddCategoryLocalDataSource,
) : CategoryRepository {
    override suspend fun addCategory(category: Category): Result<Unit> {
        return addCategoryLocalDataSource.addCategory(category = category)
    }
}