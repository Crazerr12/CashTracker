package ru.crazerr.cashtracker

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import ru.crazerr.cashtracker.umbrella.Koin

class CashTrackerApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        Koin.setupKoin {
            androidContext(this@CashTrackerApplication)
        }
    }
}
