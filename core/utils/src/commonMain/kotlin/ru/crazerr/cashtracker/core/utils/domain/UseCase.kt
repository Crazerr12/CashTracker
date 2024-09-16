package ru.crazerr.cashtracker.core.utils.domain

abstract class UseCase<out Result, in Params> {
    abstract suspend fun execute(params: Params): Result

    protected abstract fun handleResponse(throwable: Throwable): Result
}