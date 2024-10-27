package ru.crazerr.cashtracker.core.database.goal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    @ColumnInfo(name = "current_amount") val currentAmount: Float,
    @ColumnInfo(name = "target_amount") val targetAmount: Float,
    @ColumnInfo(name = "start_date") val startDate: LocalDate,
    @ColumnInfo(name = "final_date") val finalDate: LocalDate,
    val priority: Int,
    val description: String?,
)
