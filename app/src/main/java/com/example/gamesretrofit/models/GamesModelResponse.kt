package com.example.gamesretrofit.models

data class GamesModelResponse(
    val count: Int,
    val results: List<GameModel>
)

data class GameModel(
    val id: Int,
    val name: String,
    val background_image: String,
    val description_raw: String,
    val metacritic: Int,
    val website: String
)
