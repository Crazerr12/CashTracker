package ru.crazerr.cashtracker.feature.budget.data.repository

import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.feature.budget.data.getCategories.dataSource.GetCategoriesLocalDataSource
import ru.crazerr.cashtracker.feature.budget.domain.repository.CategoriesRepository
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

internal class CategoriesRepositoryImpl(
    private val getCategoriesLocalDataSource: GetCategoriesLocalDataSource,
) : CategoriesRepository {
    override suspend fun getAllCategories(): Result<List<Category>> {
        return getCategoriesLocalDataSource.getAllCategories()
            .mapCatching { categories -> categories.map { it.toCategory() } }
    }
}

internal fun CategoryEntity.toCategory() = Category(
    id = id,
    name = name,
    iconId = iconId,
    color = color
)
