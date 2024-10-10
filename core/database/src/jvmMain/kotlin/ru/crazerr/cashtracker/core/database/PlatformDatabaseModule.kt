package ru.crazerr.cashtracker.core.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

actual fun platformDatabaseModule(): Module = module {
    single<AppDatabase> { createDatabase() }
}

private fun createDatabase(): AppDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_FILE_NAME)
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
