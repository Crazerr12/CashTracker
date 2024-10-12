package ru.crazerr.cashtracker.core.utils.toast

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class ToastManagerComponent : KoinComponent {
    val toastManager: ToastManager by inject()
}

fun toastManager(): ToastManager =
    ToastManagerComponent().toastManager
