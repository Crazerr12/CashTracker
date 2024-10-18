package ru.crazerr.cashtracker.core.database.budget

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.database.category.CategoryEntity

@Entity(
    tableName = "budgetCategories",
    indices = [Index(value = ["category_id"])],
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class BudgetCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "category_id") val categoryId: Long,
    @ColumnInfo(name = "current_amount") val currentAmount: Float,
    @ColumnInfo(name = "max_amount") val maxAmount: Float,
    @ColumnInfo(name = "last_transaction_date") val lastTransactionDate: LocalDate,
)
