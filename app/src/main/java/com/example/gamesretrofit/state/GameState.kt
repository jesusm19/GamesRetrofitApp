package com.example.gamesretrofit.state

data class GameState(
    val id: Int = 0,
    val name: String = "",
    val background_image: String = "",
    val description_raw: String = "",
    val metacritic: Int = 0,
    val website: String = ""
)
