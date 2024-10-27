package ru.crazerr.cashtracker.feature.budget.domain.api.model

import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category

data class BudgetCategory(
    val id: Long,
    val category: Category,
    val currentAmount: Float,
    val maxAmount: Float,
    val lastTransactionDate: Long,
    val isRegular: Boolean,
    val nextCreationDate: LocalDate,
)
