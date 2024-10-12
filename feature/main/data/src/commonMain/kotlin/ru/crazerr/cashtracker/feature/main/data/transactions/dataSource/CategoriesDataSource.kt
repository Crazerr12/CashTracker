package ru.crazerr.cashtracker.feature.main.data.transactions.dataSource

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.category.CategoryDao
import ru.crazerr.cashtracker.core.database.category.CategoryEntity

internal class CategoriesDataSource(
    private val categoryDao: CategoryDao,
) {

    fun getCategories(): Flow<List<CategoryEntity>> {
        return categoryDao.getAll()
    }
}
