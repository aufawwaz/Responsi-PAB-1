package com.example.ppab_responsi1_kelompok09.presentation.balance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ppab_responsi1_kelompok09.domain.model.Balance
import com.example.ppab_responsi1_kelompok09.presentation.balance.components.BalanceCard
import com.example.ppab_responsi1_kelompok09.presentation.components.BottomSpacer
import com.example.ppab_responsi1_kelompok09.presentation.components.HeaderPageOnBack

@Composable
fun BalanceDetail(
    navController: NavController,
    balance: Balance
) {
    Column (
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
    ){
        // Header
        HeaderPageOnBack(
            onClick = { navController.popBackStack() },
            text = "Detail Saldo"
        )

        // Bottom Spacer
        BottomSpacer()
    }
}