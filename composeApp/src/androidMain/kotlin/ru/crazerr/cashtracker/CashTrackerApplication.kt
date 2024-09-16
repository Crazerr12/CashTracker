package ru.crazerr.cashtracker

import android.app.Application
import org.koin.core.component.KoinComponent
import org.koin.android.ext.koin.androidContext
import ru.crazerr.cashtracker.umbrella.initKoin

class CashTrackerApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@CashTrackerApplication)
        }
    }
}