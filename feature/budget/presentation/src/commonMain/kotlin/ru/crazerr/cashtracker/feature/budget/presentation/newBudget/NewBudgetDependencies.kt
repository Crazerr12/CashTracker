package ru.crazerr.cashtracker.feature.budget.presentation.newBudget

import ru.crazerr.cashtracker.feature.budget.domain.usecase.addBudgetCategory.AddBudgetCategoryUseCase
import ru.crazerr.cashtracker.feature.budget.domain.usecase.getCategories.GetCategoriesUseCase
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentFactory

internal data class NewBudgetDependencies(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val addBudgetCategoryUseCase: AddBudgetCategoryUseCase,
    val createCategoryComponentFactory: CreateCategoryComponentFactory
)
