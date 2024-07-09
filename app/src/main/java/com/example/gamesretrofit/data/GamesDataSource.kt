package com.example.gamesretrofit.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gamesretrofit.models.GameModel
import com.example.gamesretrofit.models.GamesModelResponse
import com.example.gamesretrofit.repository.GamesRepository


class GamesDataSource(private val gamesRepository: GamesRepository): PagingSource<Int, GameModel>() {
    override fun getRefreshKey(state: PagingState<Int, GameModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = gamesRepository.getGamesPAging(nextPageNumber, 3)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if(response.results.isNotEmpty()) nextPageNumber + 1 else null
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}