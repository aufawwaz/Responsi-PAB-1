package com.example.ppab_responsi1_kelompok09

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ppab_responsi1_kelompok09.presentation.components.dropShadow200
import com.example.ppab_responsi1_kelompok09.domain.model.NavItem
import com.example.ppab_responsi1_kelompok09.presentation.balance.BalanceScreen
import com.example.ppab_responsi1_kelompok09.presentation.contact.ContactDetailScreen
import com.example.ppab_responsi1_kelompok09.presentation.product.ProductScreen
import com.example.ppab_responsi1_kelompok09.presentation.contact.ContactScreen
import com.example.ppab_responsi1_kelompok09.presentation.home.HomeScreen
import com.example.ppab_responsi1_kelompok09.presentation.more.MoreScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.TransactionScreen
import com.example.ppab_responsi1_kelompok09.ui.theme.Gray
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary
import com.example.ppab_responsi1_kelompok09.ui.theme.White
import com.example.ppab_responsi1_kelompok09.presentation.login.AuthViewModel
import com.example.ppab_responsi1_kelompok09.presentation.product.ProductDetailScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.sale.BillReportScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.sale.PurchaseReportScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.sale.SaleReportScreen

@Composable
fun MainNavigation(loginNavController: NavController, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val dataClassLists = listOf(
        NavItem("home", R.drawable.ic_home, R.drawable.ic_home_fill),
        NavItem("product", R.drawable.ic_produk, R.drawable.ic_produk_fill),
        NavItem("transaction", R.drawable.ic_transaksi, R.drawable.ic_transaksi_fill),
        NavItem("contact", R.drawable.ic_pelanggan, R.drawable.ic_pelanggan_fill),
        NavItem("more", R.drawable.ic_dashboard, R.drawable.ic_dashboard_fill)
    )
    val otherScreen = listOf(
        "login",
        "register",

        "laporan_penjualan",
        "laporan_pembelian",
        "laporan_tagihan",

        "balance",

        "product_detail/{productId}",

        "contact_detail/{contactId}"
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedColor = Primary
    val unselectedColor = Gray
    val navbarColor = White

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,

        // Bottom bar ga muncul di screen dalam otherScreen
        bottomBar = {
            if (currentRoute !in otherScreen) {
                NavigationBar(
                    modifier = Modifier
                        .dropShadow200(0.dp)
                        .background(White)
                        .padding(horizontal = 4.dp),
                    containerColor = navbarColor
                ) {
                    dataClassLists.forEach { navItem ->
                        val isSelected = currentRoute == navItem.route
                        val iconRes = if (isSelected) navItem.selectedIcon else navItem.icon
                        val tintColor = if (isSelected) selectedColor else unselectedColor

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = iconRes),
                                    contentDescription = null,
                                    tint = tintColor,
                                    modifier = Modifier.size(28.dp)
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                selectedIconColor = selectedColor,
                                unselectedIconColor = unselectedColor,
                                selectedTextColor = selectedColor,
                                unselectedTextColor = unselectedColor
                            )
                        )
                    }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") { HomeScreen(navController, authViewModel) }

            composable("product") { ProductScreen(navController) }
            composable("product_detail/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")
                ProductDetailScreen(productId = productId ?: "", navController)
            }

            composable("transaction") { TransactionScreen(navController) }
            composable("laporan_penjualan") { SaleReportScreen(navController) }
            composable("laporan_pembelian") { PurchaseReportScreen(navController) }
            composable("laporan_tagihan") { BillReportScreen(navController) }

            composable("contact") { ContactScreen(navController) }
            composable("contact_detail/{contactId}") { backStackEntry ->
                val contactId = backStackEntry.arguments?.getString("contactId")
                ContactDetailScreen(navController, contactId = contactId ?: "")
            }

            composable("more") { MoreScreen(navController, loginNavController, authViewModel) }

            composable("balance") { BalanceScreen(navController) }
        }
    }
}