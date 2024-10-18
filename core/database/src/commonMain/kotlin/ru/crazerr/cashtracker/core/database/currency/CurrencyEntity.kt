package ru.crazerr.cashtracker.core.database.currency

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val code: String,
    val symbol: String,
    @ColumnInfo(name = "symbol_native")
    val symbolNative: String,
)
