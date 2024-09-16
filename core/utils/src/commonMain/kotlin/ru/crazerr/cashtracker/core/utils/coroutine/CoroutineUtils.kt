package ru.crazerr.cashtracker.core.utils.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Обертка для вызова UseCase в [Dispatchers.IO]. Использется в том случае, если
 * в `<T: ResultHandler>` есть переход на другой `Component`.
 */
suspend inline fun <T> callUseCase(
    crossinline useCase: suspend () -> T,
): T {
    return withContext(Dispatchers.IO) { useCase() }
}