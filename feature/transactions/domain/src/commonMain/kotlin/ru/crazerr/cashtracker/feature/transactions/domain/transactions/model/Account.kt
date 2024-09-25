package ru.crazerr.cashtracker.feature.transactions.domain.transactions.model

data class Account(
    val id: Long,
    val name: String,
    val balance: Float,
    val currency: String,
)
