package ru.crazerr.cashtracker.feature.main.data.categories

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.CategoriesDataSource
import ru.crazerr.cashtracker.feature.main.domain.repository.CategoryRepository

internal class CategoryRepositoryImpl(
    private val categoriesDataSource: CategoriesDataSource,
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        return categoriesDataSource.getCategories()
    }
}
