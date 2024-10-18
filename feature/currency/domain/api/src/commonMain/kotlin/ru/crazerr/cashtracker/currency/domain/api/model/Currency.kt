package ru.crazerr.cashtracker.currency.domain.api.model

data class Currency(
    val id: Long,
    val name: String,
    val code: String,
    val symbol: String,
    val symbolNative: String,
)
