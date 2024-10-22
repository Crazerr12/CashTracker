package ru.crazerr.cashtracker.feature.transactions.domain.transactions.usecase.getTransactions

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import ru.crazerr.cashtracker.core.utils.domain.UseCase
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.paging.TransactionsPagingSource
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.TransactionsRepository

interface GetTransactionsUseCase :
    UseCase<GetTransactionsUseCase.Params, Flow<PagingData<Transaction>>> {
    data class Params(
        val query: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val categoryIds: List<Long>,
        val accountIds: List<Long>,
    )
}

internal class GetTransactionsUseCaseImpl(
    private val transactionsRepository: TransactionsRepository,
) : GetTransactionsUseCase {

    override suspend fun execute(params: GetTransactionsUseCase.Params): Flow<PagingData<Transaction>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
        ) {
            TransactionsPagingSource(
                transactionsRepository = transactionsRepository,
                query = params.query,
                startDate = params.startDate.toString(),
                endDate = params.endDate.toString(),
                categoryIds = params.categoryIds,
                accountIds = params.accountIds,
            )
        }.flow
    }
}
