package ru.crazerr.cashtracker.core.database.transaction

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.transaction.model.CategoryShareDbo
import ru.crazerr.cashtracker.core.database.transaction.model.ExpensesAndIncomeDbo
import ru.crazerr.cashtracker.core.database.transaction.model.TransactionWithCategoryAndAccountDbo
import ru.crazerr.cashtracker.core.database.transaction.model.TransactionsSummaryInfoDbo

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transactionEntity: TransactionEntity): Long

    @Query("SELECT * FROM transactions WHERE id == :id")
    suspend fun getById(id: Long): TransactionEntity

    @Transaction
    @Query(
        """
        SELECT t.id AS transaction_id, t.name, t.amount, t.type, t.date, t.description,
            c.id AS category_id, c.name AS category_name, c.icon_id AS category_icon_id, c.color AS category_color,
            a.id AS account_id, a.name AS account_name, a.balance AS account_balance,
            cur.id AS account_currency_id, cur.code AS account_currency_code, cur.name AS account_currency_name, cur.symbol AS account_currency_symbol, cur.symbol_native AS account_currency_symbol_native    
        FROM transactions t
        INNER JOIN categories c ON t.category_id = c.id
        INNER JOIN accounts a ON t.account_id = a.id
        INNER JOIN currencies cur ON a.currency_id = cur.id
    """
    )
    fun getAll(): Flow<List<TransactionWithCategoryAndAccountDbo>>

    @Transaction
    @Query(
        """
        SELECT t.id AS transaction_id, t.name, t.amount, t.type, t.date, t.description,
            c.id AS category_id, c.name AS category_name, c.icon_id AS category_icon_id, c.color AS category_color,
            a.id AS account_id, a.name AS account_name, a.balance AS account_balance,
            cur.id AS account_currency_id, cur.code AS account_currency_code, cur.name AS account_currency_name, cur.symbol AS account_currency_symbol, cur.symbol_native AS account_currency_symbol_native 
        FROM transactions t
        INNER JOIN categories c ON t.category_id = c.id
        INNER JOIN accounts a ON t.account_id = a.id
        INNER JOIN currencies cur ON a.currency_id = cur.id
        WHERE strftime('%Y-%m', t.date) = :date
        ORDER BY t.date DESC
        LIMIT :limit ;
    """
    )
    fun getByMonthWithLimit(
        date: String,
        limit: Int = 10,
    ): Flow<List<TransactionWithCategoryAndAccountDbo>>

    @Transaction
    @Query(
        """
        SELECT t.id AS transaction_id, t.name, t.amount, t.type, t.date, t.description,
            c.id AS category_id, c.name AS category_name, c.icon_id AS category_icon_id, c.color AS category_color,
            a.id AS account_id, a.name AS account_name, a.balance AS account_balance, 
            cur.id AS account_currency_id, cur.code AS account_currency_code, cur.name AS account_currency_name, cur.symbol AS account_currency_symbol, cur.symbol_native AS account_currency_symbol_native  
        FROM transactions t
        INNER JOIN categories c ON t.category_id = c.id
        INNER JOIN accounts a ON t.account_id = a.id
        INNER JOIN currencies cur ON cur.id = a.currency_id
        WHERE strftime('%Y-%m', t.date) = :date
        ORDER BY t.date DESC
    """
    )
    fun getByMonth(date: String): Flow<List<TransactionWithCategoryAndAccountDbo>>

    @Transaction
    @Query(
        """
        SELECT SUM(t.amount) AS category_sum, c.id AS category_id, c.name AS category_name, c.icon_id AS category_icon_id, c.color AS category_color
        FROM transactions t
        INNER JOIN categories c ON t.category_id = c.id
        WHERE strftime('%Y-%m', t.date) = :date 
        GROUP BY t.category_id
        HAVING category_sum > 0
    """
    )
    fun getCategoryShares(date: String): Flow<List<CategoryShareDbo>>

    @Transaction
    @Query(
        """
        SELECT 
            SUM(CASE WHEN type = "INCOME" THEN amount ELSE 0 END) AS total_income,
            SUM(CASE WHEN type = "EXPENSE" THEN amount ELSE 0 END) AS total_expenses
        FROM transactions
        WHERE strftime('%Y-%m', date) = :date 
    """
    )
    fun getExpensesAndIncomeByMonth(date: String): Flow<ExpensesAndIncomeDbo>

    @Transaction
    @Query(
        """
        SELECT t.id AS transaction_id, t.name, t.amount, t.type, t.date, t.description,
            c.id AS category_id, c.name AS category_name, c.icon_id AS category_icon_id, c.color AS category_color,
            a.id AS account_id, a.name AS account_name, a.balance AS account_balance, 
            cur.id AS account_currency_id, cur.code AS account_currency_code, cur.name AS account_currency_name, cur.symbol AS account_currency_symbol, cur.symbol_native AS account_currency_symbol_native  
        FROM transactions t
        INNER JOIN categories c ON t.category_id = c.id
        INNER JOIN accounts a ON t.account_id = a.id
        INNER JOIN currencies cur ON cur.id = a.currency_id
        WHERE t.date BETWEEN :startDate AND :endDate
            AND c.id IN (:categoryIds)
            AND a.id IN (:accountIds)
            AND t.name LIKE '%' || :query || '%'
        ORDER BY t.date DESC
        LIMIT :limit OFFSET :offset
    """
    )
    fun getPagedTransactionsByDateCategoriesAndQuery(
        startDate: String,
        endDate: String,
        categoryIds: List<Long>,
        accountIds: List<Long>,
        query: String,
        limit: Int,
        offset: Int
    ): Flow<List<TransactionWithCategoryAndAccountDbo>>

    @Query(
        """
        SELECT 
            SUM(CASE WHEN t.type = "INCOME" THEN t.amount ELSE 0 END) AS income,
            SUM(CASE WHEN t.type = "EXPENSE" THEN t.amount ELSE 0 END) AS expenses
        FROM transactions t
        INNER JOIN categories c ON t.category_id = c.id
        INNER JOIN accounts a ON t.account_id = a.id
        INNER JOIN currencies cur ON cur.id = a.currency_id
        WHERE t.date BETWEEN :startDate AND :endDate
            AND c.id IN (:categoryIds)
            AND a.id IN (:accountIds)
            AND t.name LIKE '%' || :query || '%'
    """
    )
    fun getTransactionsSummaryInfo(
        startDate: String,
        endDate: String,
        categoryIds: List<Long>,
        accountIds: List<Long>,
        query: String,
    ): Flow<TransactionsSummaryInfoDbo>

    @Query(
        """
        SELECT SUM(t.amount) AS category_sum, c.id AS category_id, c.name AS category_name, c.icon_id AS category_icon_id, c.color AS category_color
        FROM transactions t
        INNER JOIN categories c ON t.category_id = c.id
        INNER JOIN accounts a ON t.account_id = a.id
        WHERE t.date BETWEEN :startDate AND :endDate
            AND c.id IN (:categoryIds)
            AND a.id IN (:accountIds)
            AND t.name LIKE '%' || :query || '%'
        GROUP BY t.category_id
        HAVING category_sum > 0
    """
    )
    fun getMostExpensiveCategories(
        startDate: String,
        endDate: String,
        categoryIds: List<Long>,
        accountIds: List<Long>,
        query: String,
    ): Flow<List<CategoryShareDbo>>

    @Update
    suspend fun update(vararg transactionEntity: TransactionEntity)

    @Delete
    suspend fun delete(vararg transactionEntity: TransactionEntity)
}
