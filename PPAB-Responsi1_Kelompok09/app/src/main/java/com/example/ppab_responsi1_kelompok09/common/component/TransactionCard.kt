package com.example.ppab_responsi1_kelompok09.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ppab_responsi1_kelompok09.common.style.AppText
import com.example.ppab_responsi1_kelompok09.data.Transaction
import com.example.ppab_responsi1_kelompok09.ui.theme.Danger
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary
import com.example.ppab_responsi1_kelompok09.ui.theme.Success
import com.example.ppab_responsi1_kelompok09.ui.theme.Warning

fun getStatusColor(status : String) : Color {
    return when (status.lowercase()) {
        "lunas" -> Success
        "diproses" -> Warning
        "jatuh tempo" -> Danger
        else -> Primary
    }
}

@Composable
fun TransactionCard (
    transaction: Transaction,
    modifier : Modifier = Modifier
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        when (transaction) {
            is Transaction.Sell -> SellCard(transaction)
            is Transaction.Purchase -> PurchaseCard(transaction)
            is Transaction.Bill -> BillCard(transaction)
        }
    }
}

@Composable
fun SellCard (data: Transaction.Sell) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        AppText(
            text = data.customer,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
        AppText(
            text = data.date,
            fontWeight = FontWeight.Light,
            fontSize = 10.sp
        )
    }
    AppText(
        text = data.id,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TonalText(
            text = data.paymentMethod,
            textColor = Primary
        )
        AppText(
            text = "Rp ${data.total}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Success
        )
    }
}

@Composable
fun PurchaseCard (data: Transaction.Purchase) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        AppText(
            text = data.seller,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
        AppText(
            text = data.date,
            fontWeight = FontWeight.Light,
            fontSize = 10.sp
        )
    }
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text = data.id,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
        AppText(
            text = "Rp ${data.total}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Danger
        )
    }
}

@Composable
fun BillCard (data: Transaction.Bill) {
    val statusColor = getStatusColor(data.status)

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        AppText(
            text = data.customer,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
        AppText(
            text = data.date,
            fontWeight = FontWeight.Light,
            fontSize = 10.sp
        )
    }
    AppText(
        text = data.id,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TonalText(
            text = data.status,
            textColor = statusColor
        )
        AppText(
            text = "Rp ${data.total}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = statusColor
        )
    }
}