package com.example.ppab_responsi1_kelompok09

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
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
//import com.example.ppab_responsi1_kelompok09.domain.repository.UserRepository
import com.example.ppab_responsi1_kelompok09.presentation.balance.BalanceDetailScreen
import com.example.ppab_responsi1_kelompok09.presentation.balance.BalanceScreen
import com.example.ppab_responsi1_kelompok09.presentation.contact.ContactDetailScreen
import com.example.ppab_responsi1_kelompok09.presentation.product.ProductScreen
import com.example.ppab_responsi1_kelompok09.presentation.contact.ContactScreen
import com.example.ppab_responsi1_kelompok09.presentation.finance.FinanceReportScreen
import com.example.ppab_responsi1_kelompok09.presentation.home.HomeScreen
import com.example.ppab_responsi1_kelompok09.presentation.more.MoreScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.TransactionScreen
import com.example.ppab_responsi1_kelompok09.ui.theme.Gray
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary
import com.example.ppab_responsi1_kelompok09.ui.theme.White
import com.example.ppab_responsi1_kelompok09.presentation.login.AuthViewModel
import com.example.ppab_responsi1_kelompok09.presentation.news.NewsDetailScreen
import com.example.ppab_responsi1_kelompok09.presentation.news.NewsScreen
import com.example.ppab_responsi1_kelompok09.presentation.product.ProductDetailScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.bill.BillDetailScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.purchase.PurchaseDetailScreen
import com.example.ppab_responsi1_kelompok09.presentation.profile.ProfileScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.sale.BillReportScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.sale.PurchaseReportScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.sale.SaleDetailScreen
import com.example.ppab_responsi1_kelompok09.presentation.transaction.sale.SaleReportScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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

        "profile/{userId}",

        "laporan_penjualan",
        "laporan_pembelian",
        "laporan_tagihan",
        "penjualan_detail/{saleId}",
        "pembelian_detail/{purchaseId}",
        "tagihan_detail/{billId}",

        "balance",
        "balance_detail/{balanceId}",

        "product_detail/{productId}",

        "contact_detail/{contactId}",

        "finance_report",
        "contact_detail/{contactId}",

        "news",
        "news_detail/{newsId}",
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
                        val isSelected = currentRoute?.startsWith(navItem.route) == true
                        val iconRes = if (isSelected) navItem.selectedIcon else navItem.icon
                        val tintColor = if (isSelected) selectedColor else unselectedColor

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    if (navItem.route == "home") {
                                        popUpTo("home") {
                                            inclusive = false
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = false
                                    } else {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = false
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
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
            composable("profile") {
                val user = authViewModel.user.collectAsState().value
                ProfileScreen(user = user, navController = navController)
            }

            composable("home") {
                val user = authViewModel.user.collectAsState().value
                HomeScreen(navController, authViewModel, user)
            }

            composable("product") { ProductScreen(navController) }
            composable("product_detail/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")
                ProductDetailScreen(productId = productId ?: "", navController)
            }

            composable("transaction?category={category}") { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category") ?: "Semua"
                TransactionScreen(navController, initialCategory = category)
            }
            composable("laporan_penjualan") { SaleReportScreen(navController) }
            composable("laporan_pembelian") { PurchaseReportScreen(navController) }
            composable("laporan_tagihan") { BillReportScreen(navController) }
            composable("penjualan_detail/{saleId}"){ backStackEntry ->
                val saleId = backStackEntry.arguments?.getString("saleId")
                SaleDetailScreen(navController, saleId = saleId ?: "")
            }
            composable("pembelian_detail/{purchaseId}"){ backStackEntry ->
                val purchaseId = backStackEntry.arguments?.getString("purchaseId")
                PurchaseDetailScreen(navController, purchaseId = purchaseId ?: "")
            }
            composable("tagihan_detail/{billId}"){ backStackEntry ->
                val billId = backStackEntry.arguments?.getString("billId")
                BillDetailScreen(navController, billId = billId ?: "")
            }

            composable("contact") { ContactScreen(navController) }
            composable("contact_detail/{contactId}") { backStackEntry ->
                val contactId = backStackEntry.arguments?.getString("contactId")
                ContactDetailScreen(navController, contactId = contactId ?: "")
            }

            composable("more") { MoreScreen(navController, loginNavController, authViewModel) }

            composable("balance") { BalanceScreen(navController) }
            composable("balance_detail/{balanceId}") { backStackEntry ->
                val balanceId = backStackEntry.arguments?.getString("balanceId")
                BalanceDetailScreen(navController, balanceId = balanceId ?: "")
            }

            composable("news"){ NewsScreen(navController) }
            composable("news_detail/{newsId}"){ backStackEntry ->
                val newsId = backStackEntry.arguments?.getString("newsId")
                NewsDetailScreen(navController, newsId = newsId ?: "")
            }

            composable("finance_report") { FinanceReportScreen(navController) }
        }
    }
}