package ru.crazerr.cashtracker.core.database.goal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Insert
    suspend fun insert(goalEntity: GoalEntity)

    @Query("SELECT * FROM goal WHERE id == :id")
    suspend fun getGoalById(id: Long): GoalEntity

    @Query("SELECT * FROM goal")
    fun getAll(): Flow<List<GoalEntity>>

    @Update
    suspend fun update(goalEntity: GoalEntity)

    @Delete
    suspend fun delete(goalEntity: GoalEntity)
}