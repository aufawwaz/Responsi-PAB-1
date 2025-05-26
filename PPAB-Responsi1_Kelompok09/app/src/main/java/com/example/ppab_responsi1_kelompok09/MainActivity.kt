package com.example.ppab_responsi1_kelompok09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ppab_responsi1_kelompok09.pages.LoginPage.LoginScreen
import com.example.ppab_responsi1_kelompok09.pages.LoginPage.RegisterScreen
import com.example.ppab_responsi1_kelompok09.ui.theme.Dark
import com.example.ppab_responsi1_kelompok09.ui.theme.ScaleUpTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val loginNavController = rememberNavController()

            ScaleUpTheme {
                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Dark.copy(0.3f),
                        darkIcons = false
                    )
                    systemUiController.setNavigationBarColor(
                        color = Dark,
                        darkIcons = false
                    )
                }

                NavHost(
                    navController = loginNavController,
                    startDestination = "login"
                ) {
                    composable("login") { LoginScreen(loginNavController) }
                    composable("register") { RegisterScreen(loginNavController) }

                    // Main screen with BottomBar
                    composable("main") {
                        MainNavigation(loginNavController)
                    }
                }
            }
        }
    }
}