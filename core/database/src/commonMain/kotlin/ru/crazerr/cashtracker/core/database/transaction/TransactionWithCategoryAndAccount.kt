package ru.crazerr.cashtracker.core.database.transaction

import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.database.account.AccountEntity
import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.core.utils.model.TransactionType

data class TransactionWithCategoryAndAccount(
    @ColumnInfo(name = "transaction_id") val id: Long,
    val name: String,
    val amount: Float,
    val type: TransactionType,
    val date: LocalDate,
    @Embedded(prefix = "category_") val category: CategoryEntity,
    @Embedded(prefix = "account_") val account: AccountEntity,
    val description: String?,
)
