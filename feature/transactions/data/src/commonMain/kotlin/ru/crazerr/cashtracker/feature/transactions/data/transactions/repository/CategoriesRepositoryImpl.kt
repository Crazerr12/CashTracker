package ru.crazerr.cashtracker.feature.transactions.data.transactions.repository

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.transactions.data.transactions.getCategories.dataSource.GetCategoriesLocalDataSource
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.CategoriesRepository

internal class CategoriesRepositoryImpl(
    private val getCategoriesLocalDataSource: GetCategoriesLocalDataSource,
) : CategoriesRepository {
    override suspend fun getCategories(): Result<List<Category>> =
        try {
            Result.success(getCategoriesLocalDataSource.getCategories().first().toCategories())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
}

internal fun List<CategoryEntity>.toCategories() =
    map {
        Category(
            id = it.id,
            name = it.name,
            iconId = it.iconId,
            color = it.color,
        )
    }
