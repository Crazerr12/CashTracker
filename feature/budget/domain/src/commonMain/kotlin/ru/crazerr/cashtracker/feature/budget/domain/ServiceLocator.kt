package ru.crazerr.cashtracker.feature.budget.domain

import org.koin.dsl.module
import ru.crazerr.cashtracker.feature.budget.domain.usecase.addBudgetCategory.AddBudgetCategoryUseCase
import ru.crazerr.cashtracker.feature.budget.domain.usecase.addBudgetCategory.AddBudgetCategoryUseCaseImpl
import ru.crazerr.cashtracker.feature.budget.domain.usecase.getCategories.GetCategoriesUseCase
import ru.crazerr.cashtracker.feature.budget.domain.usecase.getCategories.GetCategoriesUseCaseImpl

val budgetDomainModule = module {
    factory<GetCategoriesUseCase> {
        GetCategoriesUseCaseImpl(categoriesRepository = get())
    }

    factory<AddBudgetCategoryUseCase> {
        AddBudgetCategoryUseCaseImpl(budgetCategoriesRepository = get())
    }
}
