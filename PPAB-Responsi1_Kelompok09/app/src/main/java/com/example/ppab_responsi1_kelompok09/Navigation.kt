package com.example.ppab_responsi1_kelompok09

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController : NavHostController) {
    NavHost(navController = navController, startDestination = "home"){
        composable("home")      { /* HomeScreen(navController) */}
        composable("produk")    { /* ProductScreen(navController) */}
        composable("transaksi") { /* TransactionScreen(navController) */}
        composable("kontak")    { /* ContactScreen(navController) */}
        composable("more")   { /* MoreScreen(navController) */}
    }
}