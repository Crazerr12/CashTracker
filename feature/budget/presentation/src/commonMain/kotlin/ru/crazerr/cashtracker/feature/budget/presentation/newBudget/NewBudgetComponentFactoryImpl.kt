package ru.crazerr.cashtracker.feature.budget.presentation.newBudget

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.feature.budget.domain.usecase.addBudgetCategory.AddBudgetCategoryUseCase
import ru.crazerr.cashtracker.feature.budget.domain.usecase.getCategories.GetCategoriesUseCase
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponent
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponentAction
import ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget.NewBudgetComponentFactory
import ru.crazerr.cashtracker.feature.category.presentation.api.createCategoryDialog.CreateCategoryComponentFactory

internal class NewBudgetComponentFactoryImpl(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val addBudgetCategoryUseCase: AddBudgetCategoryUseCase,
    private val createCategoryComponentFactory: CreateCategoryComponentFactory,
) : NewBudgetComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (NewBudgetComponentAction) -> Unit,
    ): NewBudgetComponent =
        NewBudgetComponentImpl(
            componentContext = componentContext,
            onAction = onAction,
            dependencies = NewBudgetDependencies(
                getCategoriesUseCase = getCategoriesUseCase,
                addBudgetCategoryUseCase = addBudgetCategoryUseCase,
                createCategoryComponentFactory = createCategoryComponentFactory,
            )
        )
}
