package ru.crazerr.cashtracker.core.database.account

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.account.model.AccountWithCurrency

@Dao
interface AccountDao {

    @Insert
    suspend fun insert(accountEntity: AccountEntity): Long

    @Query(
        """
        SELECT a.id, a.name, a.balance,
            c.id AS currency_id, c.code AS currency_code, c.name AS currency_name, c.symbol AS currency_symbol, c.symbol_native AS currency_symbol_native
        FROM accounts a
        INNER JOIN currencies c ON c.id = currency_id
        WHERE a.id = :id;
    """
    )
    suspend fun getAccountById(id: Long): AccountWithCurrency

    @Query(
        """
        SELECT a.id, a.name, a.balance, 
            c.id AS currency_id, c.code AS currency_code, c.name AS currency_name, c.symbol AS currency_symbol, c.symbol_native AS currency_symbol_native
        FROM accounts a
        INNER JOIN currencies c ON c.id = currency_id;
    """
    )
    fun getAll(): Flow<List<AccountWithCurrency>>

    @Update
    suspend fun update(accountEntity: AccountEntity)

    @Delete
    suspend fun delete(accountEntity: AccountEntity)
}
