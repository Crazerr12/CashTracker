package ru.crazerr.cashtracker.feature.main.data.transactions

import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.core.database.account.model.AccountWithCurrency
import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.core.database.currency.CurrencyEntity
import ru.crazerr.cashtracker.core.database.transaction.model.CategoryShareDbo
import ru.crazerr.cashtracker.core.database.transaction.model.TransactionWithCategoryAndAccountDbo
import ru.crazerr.cashtracker.currency.domain.api.model.Currency
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.TransactionsLocalDataSource
import ru.crazerr.cashtracker.feature.main.domain.model.CategoryShare
import ru.crazerr.cashtracker.feature.main.domain.model.ExpensesAndIncome
import ru.crazerr.cashtracker.feature.main.domain.repository.TransactionsRepository
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction

internal class TransactionsRepositoryImpl(
    private val transactionsLocalDataSource: TransactionsLocalDataSource,
) : TransactionsRepository {

    override suspend fun getMonthlyTransactions(
        date: String,
        limit: Int,
    ): Result<List<Transaction>> {
        return try {
            val transactionEntities =
                transactionsLocalDataSource.getMonthlyTransactions(
                    date = date,
                    limit = limit
                ).first()
            Result.success(transactionEntities.toTransactions())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun getCategoryShares(date: String): Result<List<CategoryShare>> {
        return try {
            val categorySharesDbo =
                transactionsLocalDataSource.getCategoryShares(date = date).first()
            Result.success(categorySharesDbo.toCategoryShares())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun getExpensesAndIncomeByMonth(date: String): Result<ExpensesAndIncome> {
        return try {
            val expensesAndIncomeDbo =
                transactionsLocalDataSource.getExpensesAndIncomeByMonth(date = date).first()
            Result.success(
                ExpensesAndIncome(
                    totalExpenses = expensesAndIncomeDbo.totalExpenses,
                    totalIncome = expensesAndIncomeDbo.totalIncome
                )
            )
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}

internal fun List<TransactionWithCategoryAndAccountDbo>.toTransactions() =
    this.map { transaction ->
        Transaction(
            id = transaction.id,
            name = transaction.name,
            amount = transaction.amount,
            type = transaction.type,
            date = transaction.date,
            category = transaction.category.toCategory(),
            account = transaction.account.toAccount(),
            description = transaction.description
        )
    }

internal fun CategoryEntity.toCategory() = Category(
    id = id,
    name = name,
    iconId = iconId,
    color = color,
)

internal fun AccountWithCurrency.toAccount() = Account(
    id = id,
    name = name,
    balance = balance,
    currency = currency.toCurrency()
)

internal fun CurrencyEntity.toCurrency() = Currency(
    id = id,
    name = name,
    code = code,
    symbol = symbol,
    symbolNative = symbolNative
)

internal fun List<CategoryShareDbo>.toCategoryShares() =
    map {
        CategoryShare(
            id = it.id,
            name = it.name,
            sum = it.sum,
            percent = 0f,
            color = it.color,
            iconId = it.iconId
        )
    }
