
package com.example.gamesretrofit.repository

import com.example.gamesretrofit.data.ApiGames
import com.example.gamesretrofit.models.GameModel
import com.example.gamesretrofit.models.GamesModelResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class GamesRepository @Inject constructor(private val apiGames: ApiGames) {

    suspend fun getAllGames(): List<GameModel>? {
        val response = apiGames.getGames()

        if(response.isSuccessful) {
            return response.body()?.results
        }
        return null

    }

    suspend fun getGamesPAging(page: Int, pageSize: Int): GamesModelResponse {
        delay(3000L)
        return apiGames.getGamesPaging(page, pageSize)
    }

    suspend fun getGameById(id: Int): GameModel? {
        val response = apiGames.getGameById(id)

        if(response.isSuccessful) {
            return response.body()
        }
        return null

    }

    suspend fun getGameByName(name: String): GameModel? {
        val response = apiGames.getGameByName(name)

        if(response.isSuccessful) {
            return response.body()
        }
        return null

    }

}
