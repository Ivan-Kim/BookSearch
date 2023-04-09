package com.example.booksearch.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.booksearch.data.network.DefaultBooksRepository.Companion.NETWORK_PAGE_SIZE
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException

class BooksPagingSource @AssistedInject constructor(
    private val service: NaverApiService,
    @Assisted private val query: String,
) : PagingSource<Int, BookItem>() {

    companion object {
        private const val BOOKS_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookItem> {
        return try {
            val position = params.key ?: BOOKS_STARTING_PAGE_INDEX
            val response =
                service.fetchBooks(
                    query = query,
                    display = params.loadSize,
                    start = 1 + (position - 1) * params.loadSize
                )
            val books = response.items
            LoadResult.Page(
                data = books,
                prevKey = if (position == BOOKS_STARTING_PAGE_INDEX) null else position,
                nextKey = if (books.isEmpty()) null else position + (params.loadSize / NETWORK_PAGE_SIZE)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BookItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}