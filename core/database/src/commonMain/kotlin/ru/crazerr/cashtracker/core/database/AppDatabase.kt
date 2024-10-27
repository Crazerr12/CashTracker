package ru.crazerr.cashtracker.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.crazerr.cashtracker.core.database.account.AccountDao
import ru.crazerr.cashtracker.core.database.account.AccountEntity
import ru.crazerr.cashtracker.core.database.budget.BudgetCategoryDao
import ru.crazerr.cashtracker.core.database.budget.BudgetCategoryEntity
import ru.crazerr.cashtracker.core.database.category.CategoryDao
import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.core.database.converters.Converters
import ru.crazerr.cashtracker.core.database.currency.CurrencyDao
import ru.crazerr.cashtracker.core.database.currency.CurrencyEntity
import ru.crazerr.cashtracker.core.database.goal.GoalDao
import ru.crazerr.cashtracker.core.database.goal.GoalEntity
import ru.crazerr.cashtracker.core.database.transaction.TransactionDao
import ru.crazerr.cashtracker.core.database.transaction.TransactionEntity

@Database(
    entities = [
        CategoryEntity::class,
        AccountEntity::class,
        GoalEntity::class,
        TransactionEntity::class,
        CurrencyEntity::class,
        BudgetCategoryEntity::class
    ],
    version = DATABASE_VERSION,
)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    abstract fun accountDao(): AccountDao

    abstract fun goalDao(): GoalDao

    abstract fun transactionDao(): TransactionDao

    abstract fun currencyDao(): CurrencyDao

    abstract fun budgetCategoryDao(): BudgetCategoryDao
}

private const val DATABASE_VERSION = 1
