package ru.crazerr.cashtracker.core.database.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.crazerr.cashtracker.core.database.currency.CurrencyEntity

@Entity(
    tableName = "accounts",
    indices = [Index(value = ["currency_id"])],
    foreignKeys = [
        ForeignKey(
            entity = CurrencyEntity::class,
            parentColumns = ["id"],
            childColumns = ["currency_id"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val balance: Float,
    @ColumnInfo(name = "currency_id")
    val currencyId: Long,
)
