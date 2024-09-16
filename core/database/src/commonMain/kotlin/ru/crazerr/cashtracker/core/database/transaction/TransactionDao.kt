package ru.crazerr.cashtracker.core.database.transaction

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(vararg transactionEntity: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE id == :id")
    suspend fun getById(id: Long): TransactionEntity

    @Query("SELECT * FROM transactions")
    fun getAll(): Flow<List<TransactionEntity>>

    @Transaction
    @Query("SELECT * FROM transactions")
    fun getByMonth(): Flow<List<TransactionEntity>>

    @Update
    suspend fun update(vararg transactionEntity: TransactionEntity)

    @Delete
    suspend fun delete(vararg transactionEntity: TransactionEntity)
}