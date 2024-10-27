package ru.crazerr.cashtracker.feature.budget.data

import org.koin.dsl.module
import ru.crazerr.cashtracker.core.database.AppDatabase
import ru.crazerr.cashtracker.feature.budget.data.addBudgetCategory.dataSource.AddBudgetCategoryLocalDataSource
import ru.crazerr.cashtracker.feature.budget.data.getCategories.dataSource.GetCategoriesLocalDataSource
import ru.crazerr.cashtracker.feature.budget.data.getTransactionsAmountAndLastDate.dataSource.BudgetCategoryStatsLocalDataSource
import ru.crazerr.cashtracker.feature.budget.data.repository.BudgetCategoriesRepositoryImpl
import ru.crazerr.cashtracker.feature.budget.data.repository.CategoriesRepositoryImpl
import ru.crazerr.cashtracker.feature.budget.domain.repository.BudgetCategoriesRepository
import ru.crazerr.cashtracker.feature.budget.domain.repository.CategoriesRepository

val budgetDataModule = module {
    single<BudgetCategoriesRepository> {
        BudgetCategoriesRepositoryImpl(
            addBudgetCategoryLocalDataSource = AddBudgetCategoryLocalDataSource(
                budgetCategoryDao = get<AppDatabase>().budgetCategoryDao()
            ),
            budgetCategoryStatsLocalDataSource = BudgetCategoryStatsLocalDataSource(
                transactionDao = get<AppDatabase>().transactionDao()
            )
        )
    }

    single<CategoriesRepository> {
        CategoriesRepositoryImpl(
            getCategoriesLocalDataSource = GetCategoriesLocalDataSource(
                categoryDao = get<AppDatabase>().categoryDao()
            )
        )
    }
}
