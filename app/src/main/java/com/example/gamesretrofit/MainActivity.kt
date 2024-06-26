package com.example.gamesretrofit

import HomeView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import com.example.gamesretrofit.navigation.NavigationManager

import com.example.gamesretrofit.ui.theme.GamesRetrofitTheme
import com.example.gamesretrofit.viewModel.GamesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gamesViewModel: GamesViewModel by viewModels()
        setContent {
            GamesRetrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationManager(gamesViewModel)
                }
            }
        }
    }
}

@Composable
fun Hola() {
    Text(text = "Hola mundo")
}