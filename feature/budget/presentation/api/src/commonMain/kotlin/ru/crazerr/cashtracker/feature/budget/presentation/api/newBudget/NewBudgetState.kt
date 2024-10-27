package ru.crazerr.cashtracker.feature.budget.presentation.api.newBudget

import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

data class NewBudgetState(
    val categories: List<Category>,
    val selectedCategory: Category,
    val categorySearch: String,
    val categoryError: String?,
    val maxAmount: String,
    val maxAmountError: String?,
    val isRegular: Boolean,
    val categoriesMenuIsExpanded: Boolean,
)

internal val InitialNewBudgetState = NewBudgetState(
    categories = emptyList(),
    selectedCategory = Category(
        id = 0,
        name = "",
        iconId = "",
        color = 0
    ),
    categorySearch = "",
    categoryError = null,
    maxAmount = "",
    maxAmountError = null,
    isRegular = false,
    categoriesMenuIsExpanded = false,
)
