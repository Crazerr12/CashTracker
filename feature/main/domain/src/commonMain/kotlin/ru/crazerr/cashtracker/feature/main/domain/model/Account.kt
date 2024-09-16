package ru.crazerr.cashtracker.feature.main.domain.model

data class Account(
    val id: Long,
    val name: String,
    val balance: Float,
    val currency: String,
)
