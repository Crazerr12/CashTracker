package ru.crazerr.cashtracker.core.utils.toast

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.Job

class ToastManager {
    private var _toast: MutableValue<ToastState> = MutableValue(ToastState())
    val toast: Value<ToastState> = _toast

    private val handler: ToastHandle = ToastHandleImpl()

    private var countdownTimerJob: Job? = null

    private fun hideToast() {
        _toast.value = _toast.value.copy(
            show = false
        )
    }

    inner class ToastHandleImpl : ToastHandle {
        override fun hide() {
            hideToast()
        }
    }
}

data class ToastState(
    val show: Boolean = false,
)
