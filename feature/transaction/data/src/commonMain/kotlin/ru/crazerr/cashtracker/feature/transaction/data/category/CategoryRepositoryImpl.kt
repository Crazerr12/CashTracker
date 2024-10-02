package ru.crazerr.cashtracker.feature.transaction.data.category

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.transaction.data.category.addCategory.dataSource.AddCategoryLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.data.category.getCategories.dataSource.GetCategoriesLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.domain.repository.CategoryRepository

internal class CategoryRepositoryImpl(
    private val getCategoriesLocalDataSource: GetCategoriesLocalDataSource,
    private val addCategoryLocalDataSource: AddCategoryLocalDataSource,
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        return getCategoriesLocalDataSource.getCategories()
    }

    override suspend fun addCategory(category: Category): Result<Unit> {
        return addCategoryLocalDataSource.addCategory(category = category)
    }
}