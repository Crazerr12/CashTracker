package ru.crazerr.cashtracker.core.database.category

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(vararg categoryEntity: CategoryEntity)

    @Query("SELECT * FROM categories WHERE id == :id")
    suspend fun getCategoryById(id: Long): CategoryEntity

    @Query("SELECT * FROM categories")
    fun getAll(): Flow<List<CategoryEntity>>

    @Update
    suspend fun update(vararg categoryEntity: CategoryEntity)

    @Delete
    suspend fun delete(vararg categoryEntity: CategoryEntity)
}