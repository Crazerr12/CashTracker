package ru.crazerr.cashtracker.feature.account.domain.api.model

data class Account(
    val id: Long,
    val name: String,
    val balance: Float,
    val currency: String,
)

