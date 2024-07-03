package com.example.gamesretrofit.navigation

import HomeView
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gamesretrofit.viewModel.GamesViewModel
import com.example.gamesretrofit.views.DetailView
import com.example.gamesretrofit.views.SearchGamesView

@Composable
fun NavigationManager(gamesViewModel: GamesViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(gamesViewModel, navController)
        }

        composable("DetailView/{id}/?{name}", arguments = listOf(
            navArgument("id"){ type = NavType.IntType },
            navArgument("name"){ type = NavType.StringType },
        )){ item ->
            val id = item.arguments?.getInt("id") ?: 0
            val name = item.arguments?.getString("name") ?: ""
            DetailView(gamesViewModel, navController, id, name)
        }

        composable("SearchGamesView") {
            SearchGamesView(gamesViewModel, navController)
        }
    }
}
