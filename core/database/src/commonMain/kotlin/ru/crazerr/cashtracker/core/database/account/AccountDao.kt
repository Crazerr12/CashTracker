package ru.crazerr.cashtracker.core.database.account

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert
    suspend fun insert(accountEntity: AccountEntity)

    @Query("SELECT * FROM account WHERE id == :id")
    suspend fun getAccountById(id: Long): AccountEntity

    @Query("SELECT * FROM account")
    fun getAll(): Flow<List<AccountEntity>>

    @Update
    suspend fun update(accountEntity: AccountEntity)

    @Delete
    suspend fun delete(accountEntity: AccountEntity)
}