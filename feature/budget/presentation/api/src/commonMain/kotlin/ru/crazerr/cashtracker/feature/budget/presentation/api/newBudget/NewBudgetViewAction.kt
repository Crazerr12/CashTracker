package ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

sealed interface NewBudgetViewAction {
    data object CancelClick : NewBudgetViewAction
    data object ManageCategoryDropdown : NewBudgetViewAction
    data class UpdateCategorySearch(val search: String) : NewBudgetViewAction
    data class CategorySelect(val category: Category) : NewBudgetViewAction
    data class AmountChange(val amount: String) : NewBudgetViewAction
    data object AddNewCategoryClick : NewBudgetViewAction
    data object RegularCheckboxClick : NewBudgetViewAction
    data object CreateBudgetCategoryClick : NewBudgetViewAction
}
