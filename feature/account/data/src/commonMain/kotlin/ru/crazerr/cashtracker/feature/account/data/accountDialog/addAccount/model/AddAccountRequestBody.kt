package ru.crazerr.cashtracker.feature.account.data.accountDialog.addAccount.model

import ru.crazerr.cashtracker.core.database.account.AccountEntity

class AddAccountRequestBody(
    val name: String,
    val balance: Float,
    val currencyId: Long,
)

internal fun AddAccountRequestBody.toAccountEntity() =
    AccountEntity(
        name = name,
        balance = balance,
        currencyId = currencyId
    )
