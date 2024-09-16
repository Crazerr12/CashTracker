package ru.crazerr.cashtracker.core.database

import org.koin.core.module.Module

const val DB_FILE_NAME = "cash.db"

expect fun platformDatabaseModule(): Module