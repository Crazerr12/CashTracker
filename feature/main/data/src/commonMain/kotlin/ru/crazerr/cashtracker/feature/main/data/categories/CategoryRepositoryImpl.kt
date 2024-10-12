package ru.crazerr.cashtracker.feature.main.data.categories

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.CategoriesDataSource
import ru.crazerr.cashtracker.feature.main.domain.model.Category
import ru.crazerr.cashtracker.feature.main.domain.repository.CategoryRepository

internal class CategoryRepositoryImpl(
    private val categoriesDataSource: CategoriesDataSource,
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        val flow = categoriesDataSource.getCategories()
        val categoryEntities = flow.first()
        val categories = categoryEntities.toCategories()
        return Result.success(categories)
    }
}

fun List<CategoryEntity>.toCategories() =
    map {
        Category(
            id = it.id,
            name = it.name,
        )
    }
