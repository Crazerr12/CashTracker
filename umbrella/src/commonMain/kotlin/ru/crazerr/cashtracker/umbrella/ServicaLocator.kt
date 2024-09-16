package ru.crazerr.cashtracker.umbrella

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import ru.crazerr.cashtracker.core.database.platformDatabaseModule
import ru.crazerr.cashtracker.core.mediator.mediatorModule
import ru.crazerr.cashtracker.core.utils.utilsModule

fun initKoin(appDeclaration: KoinAppDeclaration = { }) {
    startKoin {
        appDeclaration()
        modules(mediatorModule, platformDatabaseModule(), utilsModule)
    }
}