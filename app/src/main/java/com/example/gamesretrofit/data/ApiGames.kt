
package com.example.gamesretrofit.data

import com.example.gamesretrofit.models.GameModel
import com.example.gamesretrofit.models.GamesModelResponse
import com.example.gamesretrofit.util.Constants.Companion.API_KEY
import com.example.gamesretrofit.util.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiGames {

    @GET(ENDPOINT + API_KEY)
    suspend fun getGames(): Response<GamesModelResponse>

    @GET("$ENDPOINT/{id}$API_KEY")
    suspend fun getGameById(@Path(value = "id") id: Int): Response<GameModel>

    @GET("$ENDPOINT/{name}$API_KEY")
    suspend fun getGameByName(@Path(value = "name") name: String): Response<GameModel>

}
