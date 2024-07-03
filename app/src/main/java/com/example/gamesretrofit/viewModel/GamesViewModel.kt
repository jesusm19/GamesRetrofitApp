

package com.example.gamesretrofit.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamesretrofit.models.GameModel
import com.example.gamesretrofit.repository.GamesRepository
import com.example.gamesretrofit.state.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(private val gamesRepository: GamesRepository): ViewModel() {

    private val _listGames = MutableStateFlow<List<GameModel>>(emptyList())
    val listGames = _listGames.asStateFlow()

    var gameState by mutableStateOf(GameState())
        private set


    init{
        try {
            fetchGames()
        } catch (e: Exception){
            print("ERROR --> ${e.message}")
        }


    }

    private fun fetchGames() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = gamesRepository.getAllGames()
                _listGames.value = result ?: emptyList()
            }

        }
    }

    fun fetchGameById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = gamesRepository.getGameById(id)

                println("Nombre de juego + ${result?.name}")

                gameState = gameState.copy(
                    name = result?.name ?: "",
                    background_image = result?.background_image ?: "",
                    description_raw = result?.description_raw ?: "",
                    metacritic = result?.metacritic ?: 123,
                    website = result?.website ?: "Sin website"
                )


            }
        }
    }

    fun fetchGameByName(name: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = gamesRepository.getGameByName(name)
                println("REsultado -> " + result)
                println("Nombre de juego + ${result?.name}")

                gameState = gameState.copy(
                    name = result?.name ?: "",
                    background_image = result?.background_image ?: "",
                    description_raw = result?.description_raw ?: "",
                    metacritic = result?.metacritic ?: 123,
                    website = result?.website ?: "Sin website"
                )


            }
        }
    }

    fun limpiarGameState(){
        gameState = gameState.copy(
            name = "",
            background_image = "",
            description_raw = "",
            metacritic = 0,
            website = ""
        )
    }


}

