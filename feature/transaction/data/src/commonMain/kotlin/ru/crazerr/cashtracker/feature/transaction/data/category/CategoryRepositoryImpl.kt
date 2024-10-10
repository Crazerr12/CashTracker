package ru.crazerr.cashtracker.feature.transaction.data.category

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.transaction.data.category.getCategories.dataSource.GetCategoriesLocalDataSource
import ru.crazerr.cashtracker.feature.transaction.domain.repository.CategoryRepository

internal class CategoryRepositoryImpl(
    private val getCategoriesLocalDataSource: GetCategoriesLocalDataSource,
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        return getCategoriesLocalDataSource.getCategories()
    }
}