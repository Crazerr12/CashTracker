package ru.crazerr.cashtracker.core.database.currency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Insert
    suspend fun insert(currencyEntity: CurrencyEntity): Long

    @Query("SELECT * FROM currencies WHERE id = :id")
    suspend fun getById(id: Long): CurrencyEntity

    @Query("SELECT * FROM currencies")
    fun getAll(): Flow<List<CurrencyEntity>>
}
