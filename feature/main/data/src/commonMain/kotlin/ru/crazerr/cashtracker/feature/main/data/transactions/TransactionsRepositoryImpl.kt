package ru.crazerr.cashtracker.feature.main.data.transactions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.crazerr.cashtracker.core.database.account.AccountEntity
import ru.crazerr.cashtracker.core.database.category.CategoryEntity
import ru.crazerr.cashtracker.core.database.transaction.TransactionEntity
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.AccountsDataSource
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.CategoriesDataSource
import ru.crazerr.cashtracker.feature.main.data.transactions.dataSource.TransactionsDataSource
import ru.crazerr.cashtracker.feature.main.domain.model.Account
import ru.crazerr.cashtracker.feature.main.domain.model.Category
import ru.crazerr.cashtracker.feature.main.domain.model.Transaction
import ru.crazerr.cashtracker.feature.main.domain.repository.TransactionsRepository

internal class TransactionsRepositoryImpl(
    private val transactionsDataSource: TransactionsDataSource,
    private val accountsDataSource: AccountsDataSource,
    private val categoriesDataSource: CategoriesDataSource,
) : TransactionsRepository {

    override suspend fun getMonthlyTransactions(): Result<List<Transaction>> {
        return withContext(Dispatchers.IO) {
            val accountsDeferred = async { accountsDataSource.getAccounts() }
            val categoriesDeferred = async { categoriesDataSource.getCategories() }

            val transactionEntities = transactionsDataSource.getMonthlyTransactions().first()

            val accounts = accountsDeferred.await().first()
            val categories = categoriesDeferred.await().first()
            val transactions = transactionEntities.toTransactions(accounts, categories)

            Result.success(transactions)
        }
    }
}

private fun List<TransactionEntity>.toTransactions(
    accounts: List<AccountEntity>,
    categories: List<CategoryEntity>,
) =
    map { transaction ->
        val category = categories.first { it.id == transaction.categoryId }
        val account = accounts.first { it.id == transaction.accountId }
        Transaction(
            id = transaction.id,
            name = transaction.name,
            amount = transaction.amount,
            type = transaction.type,
            date = transaction.date,
            category = Category(
                id = category.id,
                name = category.name
            ),
            account = Account(
                id = account.id,
                name = account.name,
                balance = account.balance,
                currency = account.currency
            ),
            description = transaction.description
        )
    }
