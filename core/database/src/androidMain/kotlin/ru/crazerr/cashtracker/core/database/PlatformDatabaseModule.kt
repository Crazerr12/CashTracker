package ru.crazerr.cashtracker.core.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformDatabaseModule(): Module = module {
    single<AppDatabase> { createDatabase(get()) }
}

private fun createDatabase(
    context: Context,
): AppDatabase = Room.databaseBuilder<AppDatabase>(
    context = context.applicationContext,
    name = context.applicationContext.getDatabasePath(DB_FILE_NAME).absolutePath
).setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    .createFromAsset("database/finance.db")
    .build()
