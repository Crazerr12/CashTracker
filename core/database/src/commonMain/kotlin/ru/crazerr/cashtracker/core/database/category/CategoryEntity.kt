package ru.crazerr.cashtracker.core.database.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorie")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
)
