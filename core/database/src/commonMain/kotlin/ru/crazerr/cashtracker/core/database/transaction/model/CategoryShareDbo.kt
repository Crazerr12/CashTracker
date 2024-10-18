package ru.crazerr.cashtracker.core.database.transaction.model

import androidx.room.ColumnInfo

data class CategoryShareDbo(
    @ColumnInfo(name = "category_id") val id: Long,
    @ColumnInfo(name = "category_name") val name: String,
    @ColumnInfo(name = "category_sum") val sum: Float,
    @ColumnInfo(name = "category_icon_id") val iconId: String,
    @ColumnInfo(name = "category_color") val color: Int,
)
