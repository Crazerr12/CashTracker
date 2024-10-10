package ru.crazerr.cashtracker.core.utils.navigationDrawer

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class NavigationDrawerComponent : KoinComponent {
    val navigationDrawerManager: NavigationDrawerManager by inject()
}

fun navigationDrawerManager(): NavigationDrawerManager =
    NavigationDrawerComponent().navigationDrawerManager
