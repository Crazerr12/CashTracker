package ru.crazerr.cashtracker.feature.transactions.domain.transactions.model

import kotlinx.datetime.LocalDate

data class TransactionHeader(
    val date: LocalDate,
    val isExpand: Boolean = false,
)
