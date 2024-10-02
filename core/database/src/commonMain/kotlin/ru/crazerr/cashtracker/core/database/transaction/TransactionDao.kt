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

    @Transaction
    @Query(
        """
        SELECT t.id AS transaction_id, t.name, t.amount, t.type, t.date, t.description,
            c.id AS category_id, c.name AS category_name,
            a.id AS account_id, a.name AS account_name, a.balance AS account_balance, a.currency AS account_currency
        FROM transactions t
        INNER JOIN categories c ON t.category_id = c.id
        INNER JOIN accounts a ON t.account_id = a.id
    """
    )
    fun getAll(): Flow<List<TransactionWithCategoryAndAccount>>

    @Query("SELECT * FROM transactions")
    fun getByMonth(): Flow<List<TransactionEntity>>

    @Update
    suspend fun update(vararg transactionEntity: TransactionEntity)

    @Delete
    suspend fun delete(vararg transactionEntity: TransactionEntity)
}