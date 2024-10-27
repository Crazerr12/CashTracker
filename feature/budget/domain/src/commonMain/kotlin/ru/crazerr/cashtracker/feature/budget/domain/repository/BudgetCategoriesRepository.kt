package ru.crazerr.cashtracker.feature.budget.domain.repository

import kotlinx.datetime.LocalDate

interface BudgetCategoriesRepository {
    suspend fun addBudgetCategory(
        categoryId: Long,
        maxAmount: Float,
        isRegular: Boolean,
        nextCreationDate: LocalDate?,
    ): Result<Unit>
}
