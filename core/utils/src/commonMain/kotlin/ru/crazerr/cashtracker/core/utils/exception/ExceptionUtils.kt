package ru.crazerr.cashtracker.core.utils.exception

fun <T> Throwable.fold(
    onApiError: ((ApiMethodError) -> T)? = null,
    onNetworkError: ((Throwable) -> T)? = null,
    onElse: (Throwable) -> T,
): T {
    return when {
        this is ApiMethodError && onApiError != null -> onApiError(this)
        this is NetworkError && onNetworkError != null -> onNetworkError(this)
        else -> onElse(this)
    }
}
