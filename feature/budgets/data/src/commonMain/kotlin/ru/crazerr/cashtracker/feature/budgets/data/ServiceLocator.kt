package ru.crazerr.cashtracker.feature.budgets.data

import org.koin.dsl.module
import ru.crazerr.cashtracker.core.database.AppDatabase
import ru.crazerr.cashtracker.feature.budgets.data.deleteBudget.dataSource.DeleteBudgetLocalDataSource
import ru.crazerr.cashtracker.feature.budgets.data.getBudgets.dataSource.GetBudgetsLocalDataSource
import ru.crazerr.cashtracker.feature.budgets.data.repository.BudgetsRepositoryImpl
import ru.crazerr.cashtracker.feature.budgets.domain.repository.BudgetsRepository

val budgetsDataModule = module {
    single<BudgetsRepository> {
        BudgetsRepositoryImpl(
            getBudgetsLocalDataSource = GetBudgetsLocalDataSource(
                budgetCategoryDao = get<AppDatabase>().budgetCategoryDao()
            ),
            deleteBudgetLocalDataSource = DeleteBudgetLocalDataSource(
                budgetCategoryDao = get<AppDatabase>().budgetCategoryDao()
            )
        )
    }
}
