package ru.crazerr.cashtracker.core.utils

import ru.crazerr.cashtracker.core.utils.toast.toastManager

abstract class ResultHandler {
    protected val toastManager = toastManager()

    abstract fun handle()

    protected fun onNetworkError(action: (() -> Unit)? = null) {
        action?.invoke()
        toastManager
    }

    protected fun onUnknownError(t: Throwable?, action: (() -> Unit)? = null) {
        action?.invoke()
        toastManager
    }
}