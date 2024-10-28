package ru.crazerr.cashtracker.core.database.budget

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.core.utils.dateTime.now

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
    @ColumnInfo(name = "is_regular", defaultValue = "0") val isRegular: Boolean,
    @ColumnInfo(name = "next_creation_date") val nextCreationDate: LocalDate?,
    @ColumnInfo(name = "creation_date") val creationDate: LocalDate = LocalDate.now()
)
