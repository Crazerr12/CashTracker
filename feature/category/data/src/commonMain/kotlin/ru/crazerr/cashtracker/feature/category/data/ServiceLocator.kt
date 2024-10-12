package ru.crazerr.cashtracker.feature.category.data

import org.koin.dsl.module
import ru.crazerr.cashtracker.core.database.AppDatabase
import ru.crazerr.cashtracker.feature.category.data.addCategory.dataSource.AddCategoryLocalDataSource
import ru.crazerr.cashtracker.feature.category.domain.repository.CategoryRepository

val categoryDataModule = module {
    single<CategoryRepository> {
        CategoryRepositoryImpl(
            addCategoryLocalDataSource = AddCategoryLocalDataSource(categoryDao = get<AppDatabase>().categoryDao())
        )
    }
}
