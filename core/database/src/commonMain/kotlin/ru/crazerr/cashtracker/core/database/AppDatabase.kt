package ru.crazerr.cashtracker.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.crazerr.cashtracker.core.database.account.AccountDao
import ru.crazerr.cashtracker.core.database.account.AccountEntity
import ru.crazerr.cashtracker.core.database.category.CategoryDao
import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.core.database.converters.Converters
import ru.crazerr.cashtracker.core.database.goal.GoalDao
import ru.crazerr.cashtracker.core.database.goal.GoalEntity
import ru.crazerr.cashtracker.core.database.transaction.TransactionDao
import ru.crazerr.cashtracker.core.database.transaction.TransactionEntity

@Database(
    entities = [
        CategoryEntity::class,
        GoalEntity::class,
        AccountEntity::class,
        TransactionEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = true,
)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    abstract fun accountDao(): AccountDao

    abstract fun goalDao(): GoalDao

    abstract fun transactionDao(): TransactionDao
}

private const val DATABASE_VERSION = 2
