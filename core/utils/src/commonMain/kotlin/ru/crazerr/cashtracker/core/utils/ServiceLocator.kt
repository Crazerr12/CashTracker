package ru.crazerr.cashtracker.core.utils

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.crazerr.cashtracker.core.utils.navigationDrawer.NavigationDrawerManager
import ru.crazerr.cashtracker.core.utils.toast.ToastManager

val utilsModule: Module = module {
    single { ToastManager() }
    single { NavigationDrawerManager() }
}
