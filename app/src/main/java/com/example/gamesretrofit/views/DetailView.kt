package com.example.gamesretrofit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamesretrofit.components.MainImage
import com.example.gamesretrofit.components.MainTopBar
import com.example.gamesretrofit.components.MetaWebSite
import com.example.gamesretrofit.components.ReviewCard
import com.example.gamesretrofit.state.GameState
import com.example.gamesretrofit.util.Constants.Companion.CUSTOM_COLOR
import com.example.gamesretrofit.viewModel.GamesViewModel

@Composable
fun DetailView(gamesViewModel: GamesViewModel, navController: NavController, id: Int, name: String?) {

    LaunchedEffect(Unit) {
        if (id == 0) {
            if (name != null) {
                gamesViewModel.fetchGameByName(name.replace(" ","-"))
            }
        } else {
            gamesViewModel.fetchGameById(id)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            gamesViewModel.limpiarGameState()
        }
    }

    val gameState = gamesViewModel.gameState
    Scaffold(
        topBar = {
            MainTopBar(title = gameState.name,
                true,
                onClickBackButton = {
                    navController.popBackStack()
                }) { }
        }
    ) {
        ContentDetailView(it, gamesViewModel, gameState)
    }


}

@Composable
fun ContentDetailView(paddingValues: PaddingValues, gamesViewModel: GamesViewModel, gameState: GameState) {

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(Color(CUSTOM_COLOR))
    ) {
        MainImage(image = gameState.background_image)
        Spacer(modifier = Modifier.height(10.dp))

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 5.dp)
        ){
            MetaWebSite(url = gameState.website)
            ReviewCard(metaScore = gameState.metacritic)

        }

        val scroll = rememberScrollState(0)
        Text(
            text = gameState.description_raw,
            color = Color.White,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                .verticalScroll(scroll)
        )
        
    }

}
