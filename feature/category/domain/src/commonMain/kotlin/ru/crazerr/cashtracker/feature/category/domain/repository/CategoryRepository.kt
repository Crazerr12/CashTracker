package ru.crazerr.cashtracker.feature.category.domain.repository

interface CategoryRepository {
    suspend fun addCategory(name: String, color: Int, iconId: String): Result<Long>
}
