package ru.crazerr.cashtracker.feature.transactions.domain.transactions.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.first
import ru.crazerr.cashtracker.feature.transaction.domain.api.model.Transaction
import ru.crazerr.cashtracker.feature.transactions.domain.transactions.repository.TransactionsRepository

internal class TransactionsPagingSource(
    private val transactionsRepository: TransactionsRepository,
    private val query: String,
    private val startDate: String,
    private val endDate: String,
    private val accountIds: List<Long>,
    private val categoryIds: List<Long>,
) : PagingSource<Int, Transaction>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Transaction> {
        val page = params.key ?: INITIAL_PAGE_NUMBER
        val pageSize = params.loadSize

        return try {
            val data = transactionsRepository.getTransactionsByDateAndCategoriesAndAccountsPaged(
                query = query,
                startDate = startDate,
                endDate = endDate,
                accountIds = accountIds,
                categoryIds = categoryIds,
                limit = pageSize,
                offset = page * pageSize
            ).first()

            LoadResult.Page(
                data = data,
                prevKey = if (page > INITIAL_PAGE_NUMBER) page - 1 else null,
                nextKey = if (data.isEmpty()) null else page + 1,
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Transaction>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val INITIAL_PAGE_NUMBER = 0
    }
}
