
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamesretrofit.components.CardGame
import com.example.gamesretrofit.components.MainTopBar
import com.example.gamesretrofit.util.Constants.Companion.CUSTOM_COLOR
import com.example.gamesretrofit.viewModel.GamesViewModel

@Composable
fun HomeView(gamesViewModel: GamesViewModel, navController: NavController){
    Scaffold(
        topBar = {
            MainTopBar(title = "API GAMES", onClickBackButton = {}) {
                navController.navigate("SearchGamesView")
            }
        }
    ) {
        ContentHomeView(gamesViewModel, it, navController)
    }

}

@Composable
fun ContentHomeView(
    gamesViewModel: GamesViewModel,
    paddingValues: PaddingValues,
    navController: NavController,
) {
    val listGames by gamesViewModel.listGames.collectAsState()

    LazyColumn(modifier = Modifier
        .padding(paddingValues)
        .background(Color(CUSTOM_COLOR))) {
        items(listGames) { game ->

            CardGame(game) {
                navController.navigate("DetailView/${game.id}")
            }

            Text(
                text = game.name,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )

        }
    }
}
