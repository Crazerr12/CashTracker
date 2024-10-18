package ru.crazerr.cashtracker.feature.account.domain.api.model

import ru.crazerr.cashtracker.currency.domain.api.model.Currency

data class Account(
    val id: Long,
    val name: String,
    val balance: Float,
    val currency: Currency,
)
