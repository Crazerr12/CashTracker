package ru.crazerr.cashtracker.core.utils.domain

interface UseCase<in Params : Any, out Result : Any> {
    suspend fun execute(params: Params): Result
}
