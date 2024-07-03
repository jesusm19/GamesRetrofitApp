
package com.example.gamesretrofit.repository

import com.example.gamesretrofit.data.ApiGames
import com.example.gamesretrofit.models.GameModel
import javax.inject.Inject

class GamesRepository @Inject constructor(private val apiGames: ApiGames) {

    suspend fun getAllGames(): List<GameModel>? {
        val response = apiGames.getGames()

        if(response.isSuccessful) {
            return response.body()?.results
        }
        return null

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
