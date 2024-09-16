package ru.crazerr.cashtracker.core.utils.exception

class ApiMethodError(val error: Error? = null) : Exception(error?.message) {

    override fun toString(): String {
        return "ApiMethodError=($error)"
    }
}