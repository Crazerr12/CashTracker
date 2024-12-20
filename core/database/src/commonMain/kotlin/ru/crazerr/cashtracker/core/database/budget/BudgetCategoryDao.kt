package ru.crazerr.cashtracker.core.database.budget

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.crazerr.cashtracker.core.database.budget.model.BudgetCategoryWithCategory

@Dao
interface BudgetCategoryDao {
    @Insert
    suspend fun insert(budgetCategoryEntity: BudgetCategoryEntity)

    @Query(
        """
        SELECT b.id, b.current_amount, b.max_amount, b.last_transaction_date, b.is_regular, b.next_creation_date,
            c.id AS category_id, c.name AS category_name, c.icon_id AS category_icon_id, c.color AS category_color
        FROM budgetCategories b
        INNER JOIN categories c ON b.category_id = c.id 
    """
    )
    fun getAll(): Flow<List<BudgetCategoryWithCategory>>

    @Update
    suspend fun update(budgetCategoryEntity: BudgetCategoryEntity)

    @Query(
        """
        SELECT *
        FROM budgetCategories
        WHERE creation_date BETWEEN :startOfMonthDate AND :endOfMonthDate
            AND category_id = :categoryId
    """
    )
    suspend fun getByCategoryId(
        categoryId: Long,
        startOfMonthDate: String,
        endOfMonthDate: String
    ): BudgetCategoryEntity

    @Query("DELETE FROM budgetCategories WHERE id = :id")
    suspend fun deleteById(id: Long)
}
