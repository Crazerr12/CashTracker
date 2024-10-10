package ru.crazerr.cashtracker.core.utils.exception

import java.io.IOException
import java.net.SocketTimeoutException

class NetworkError(ref: Throwable) : Exception(ref.message, ref.cause)

fun Throwable.isNetwork() =
    this is SocketTimeoutException || this is IOException
