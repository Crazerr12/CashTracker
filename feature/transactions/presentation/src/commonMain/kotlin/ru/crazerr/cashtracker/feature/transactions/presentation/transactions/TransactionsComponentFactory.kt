package ru.crazerr.cashtracker.feature.transactions.presentation.transactions

import com.arkivanov.decompose.ComponentContext
import ru.crazerr.cashtracker.core.utils.presentation.ComponentFactory
import ru.crazerr.cashtracker.feature.transaction.presentation.api.createTransactionDialog.CreateTransactionComponentFactory
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getAccounts.GetAccountsUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getCategories.GetCategoriesUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getSummary.GetSummaryInfoUseCase
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getTransactions.GetTransactionsUseCase

interface TransactionsComponentFactory :
    ComponentFactory<TransactionsComponent, TransactionsComponentAction>

internal class TransactionsComponentFactoryImpl(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val createTransactionComponentFactory: CreateTransactionComponentFactory,
    private val getAccountsUseCase: GetAccountsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getSummaryInfoUseCase: GetSummaryInfoUseCase,
) : TransactionsComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        onAction: (TransactionsComponentAction) -> Unit,
    ) = TransactionsComponent(
        componentContext = componentContext,
        onAction = onAction,
        dependencies = TransactionsDependencies(
            getTransactionsUseCase = getTransactionsUseCase,
            createTransactionsComponentFactory = createTransactionComponentFactory,
            getAccountsUseCase = getAccountsUseCase,
            getCategoriesUseCase = getCategoriesUseCase,
            getSummaryInfoUseCase = getSummaryInfoUseCase
        )
    )
}
