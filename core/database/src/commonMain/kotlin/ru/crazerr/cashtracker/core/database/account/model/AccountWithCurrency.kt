package ru.crazerr.cashtracker.core.database.account.model

import androidx.room.Embedded
import ru.crazerr.cashtracker.core.database.currency.CurrencyEntity

data class AccountWithCurrency(
    val id: Long = 0,
    val name: String,
    val balance: Float,
    @Embedded(prefix = "currency_") val currency: CurrencyEntity,
)
