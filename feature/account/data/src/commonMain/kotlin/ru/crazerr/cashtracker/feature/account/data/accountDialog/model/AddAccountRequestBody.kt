package ru.crazerr.cashtracker.feature.account.data.accountDialog.model

import ru.crazerr.cashtracker.core.database.account.AccountEntity

class AddAccountRequestBody(
    val name: String,
    val balance: Float,
    val currency: String,
)

internal fun AddAccountRequestBody.toAccountEntity() =
    AccountEntity(
        name = name,
        balance = balance,
        currency = currency
    )
