package ru.crazerr.cashtracker.core.database.budget.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.database.category.CategoryEntity

data class BudgetCategoryWithCategory(
    val id: Long,
    @Embedded(prefix = "category_") val category: CategoryEntity,
    @ColumnInfo(name = "current_amount") val currentAmount: Float,
    @ColumnInfo(name = "max_amount") val maxAmount: Float,
    @ColumnInfo(name = "last_transaction_date") val lastTransactionDate: LocalDate,
)
