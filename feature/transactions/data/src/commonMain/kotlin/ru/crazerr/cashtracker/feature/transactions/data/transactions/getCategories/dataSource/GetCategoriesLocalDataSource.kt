package ru.crazerr.cashtracker.feature.transactions.data.transactions.getCategories.dataSource

import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.category.CategoryDao
import ru.crazerr.cashtracker.core.database.category.CategoryEntity

internal class GetCategoriesLocalDataSource(
    private val categoryDao: CategoryDao,
) {
    fun getCategories(): Flow<List<CategoryEntity>> {
        return categoryDao.getAll()
    }
}
