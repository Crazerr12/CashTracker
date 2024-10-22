package ru.crazerr.cashtracker.feature.transactions.data.transactions.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import ru.crazerr.cashtracker.core.database.transaction.model.TransactionWithCategoryAndAccountDbo
import ru.crazerr.cashtracker.currency.domain.api.model.Currency
import ru.crazerr.cashtracker.feature.account.domain.api.model.Account
import ru.crazerr.cashtracker.feature.category.domain.api.model.Category
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction
import ru.crazerr.cashtracker.feature.transactions.data.transactions.getSummaryInfoAboutTransactions.dataSource.GetTransactionsSummaryInfoLocalDataSource
import ru.crazerr.cashtracker.feature.transactions.data.transactions.getTransactions.dataSource.GetTransactionsLocalDataSource
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.CategoryShare
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.model.SummaryInfo
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.TransactionsRepository

internal class TransactionsRepositoryImpl(
    private val getTransactionsLocalDataSource: GetTransactionsLocalDataSource,
    private val getTransactionsSummaryInfoLocalDataSource: GetTransactionsSummaryInfoLocalDataSource,

) : TransactionsRepository {
    override suspend fun getTransactionsByDateAndCategoriesAndAccountsPaged(
        query: String,
        startDate: String,
        endDate: String,
        categoryIds: List<Long>,
        accountIds: List<Long>,
        limit: Int,
        offset: Int,
    ): Flow<List<Transaction>> {
        return getTransactionsLocalDataSource.getTransactionsByDateAndQueryAndPaged(
            query = query,
            startDate = startDate,
            endDate = endDate,
            categoryIds = categoryIds,
            accountIds = accountIds,
            limit = limit,
            offset = offset,
        ).map { transactionEntities -> transactionEntities.map { it.toTransaction() } }
    }

    override fun getTransactionsSummaryInfo(
        query: String,
        startDate: String,
        endDate: String,
        categoryIds: List<Long>,
        accountIds: List<Long>,
    ): Result<Flow<SummaryInfo>> {
        return try {
            val incomeAndExpensesFlow =
                getTransactionsSummaryInfoLocalDataSource.getTransactionsSummaryInfo(
                    startDate = startDate,
                    endDate = endDate,
                    categoryIds = categoryIds,
                    query = query,
                    accountIds = accountIds
                )
            val categoryShareFlow =
                getTransactionsSummaryInfoLocalDataSource.getMostExpensiveCategories(
                    startDate = startDate,
                    endDate = endDate,
                    categoryIds = categoryIds,
                    query = query,
                    accountIds = accountIds
                )

            Result.success(
                incomeAndExpensesFlow.combine(categoryShareFlow) { mostExpensiveCategories, summary ->
                    SummaryInfo(
                        income = mostExpensiveCategories.income,
                        expenses = mostExpensiveCategories.expenses,
                        mostExpensiveCategories = summary.map {
                            CategoryShare(
                                id = it.id,
                                name = it.name,
                                sum = it.sum,
                                color = it.color,
                                iconId = it.iconId
                            )
                        }
                    )
                }
            )
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}

internal fun TransactionWithCategoryAndAccountDbo.toTransaction() = Transaction(
    id = this.id,
    name = this.name,
    amount = this.amount,
    type = this.type,
    date = this.date,
    category = Category(
        id = this.category.id,
        name = this.category.name,
        iconId = this.category.iconId,
        color = this.category.color,
    ),
    account = Account(
        id = this.account.id,
        name = this.account.name,
        balance = this.account.balance,
        currency = Currency(
            id = this.account.currency.id,
            name = this.account.currency.name,
            code = this.account.currency.code,
            symbol = this.account.currency.symbol,
            symbolNative = this.account.currency.symbolNative,
        )
    ),
    description = this.description
)
