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

        composable("DetailView/{id}", arguments = listOf(
            navArgument("id"){ type = NavType.IntType }
        )){
            val id = it.arguments?.getInt("id") ?: 0
            DetailView(gamesViewModel, navController, id)
        }

        composable("SearchGamesView") {
            SearchGamesView(gamesViewModel, navController)
        }
    }
}
