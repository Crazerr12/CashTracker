package ru.crazerr.cashtracker.core.utils.presentation

sealed interface DataState<out T> {
    data class Success<T>(val value: T) : DataState<T>
    class Loading<T> : DataState<T>
    data class Error<T>(val isNetwork: Boolean = false, val message: String? = null) : DataState<T>
}

val <T> DataState<T>.value: T?
    get() = if (this is DataState.Success) {
        value
    } else {
        null
    }
