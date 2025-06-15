package com.example.ppab_responsi1_kelompok09.presentation.finance

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ppab_responsi1_kelompok09.R
import com.example.ppab_responsi1_kelompok09.domain.model.Transaction
import com.example.ppab_responsi1_kelompok09.domain.repository.TransactionRepository
import com.example.ppab_responsi1_kelompok09.presentation.components.AppText
import com.example.ppab_responsi1_kelompok09.presentation.components.DateFilter
import com.example.ppab_responsi1_kelompok09.presentation.components.DateFilterOverlay
import com.example.ppab_responsi1_kelompok09.presentation.components.DrawLine
import com.example.ppab_responsi1_kelompok09.presentation.components.HeaderPageOnBack
import com.example.ppab_responsi1_kelompok09.presentation.components.TonalText
import com.example.ppab_responsi1_kelompok09.presentation.components.dropShadow200
import com.example.ppab_responsi1_kelompok09.presentation.components.formatToCurrency
import com.example.ppab_responsi1_kelompok09.presentation.components.getDateRangeValue
import com.example.ppab_responsi1_kelompok09.presentation.components.getPrevPeriodLabel
import com.example.ppab_responsi1_kelompok09.presentation.components.getPreviousDateRange
import com.example.ppab_responsi1_kelompok09.presentation.transaction.component.DateFilterButton
import com.example.ppab_responsi1_kelompok09.ui.theme.Danger
import com.example.ppab_responsi1_kelompok09.ui.theme.Dark
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary100
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary300
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary500
import com.example.ppab_responsi1_kelompok09.ui.theme.Success
import com.example.ppab_responsi1_kelompok09.ui.theme.White
import java.time.ZoneId
import kotlin.collections.plusAssign
import kotlin.div
import kotlin.text.compareTo
import kotlin.text.toFloat
import kotlin.times

@Composable
fun FinanceReportScreen(navController: NavController) {
    // Buat filter tanggal
    var showOverlay by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf(DateFilter.TODAY) }
    val (startDate, endDate) = getDateRangeValue(selectedFilter)

    // Ambil transaksi
    val transaction = TransactionRepository.getAllTransaction()

    val filteredTransaction = transaction.filter {
        val date = when (it) {
            is Transaction.Sell -> it.date
            is Transaction.Purchase -> it.date
            is Transaction.Bill -> it.date
        }
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        localDate in startDate..endDate
    }

    val (prevStart, prevEnd) = getPreviousDateRange(selectedFilter)
    val prevTransaction = transaction.filter {
        val date = when (it) {
            is Transaction.Sell -> it.date
            is Transaction.Purchase -> it.date
            is Transaction.Bill -> it.date
        }
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        localDate in prevStart..prevEnd
    }

    val prevCount = prevTransaction.size
    val currCount = filteredTransaction.size
    val percentChange = when {
        prevCount == 0 && currCount == 0 -> 0f
        prevCount == 0 -> 100f
        else -> ((currCount - prevCount) / prevCount.toFloat()) * 100
    }
    val isUp = currCount >= prevCount

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            HeaderPageOnBack(
                onClick = { navController.popBackStack() },
                text = "Laporan Penjualan"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(Modifier.height(1.dp))
                DateFilterButton(
                    onClickShowOverlay = { showOverlay = true },
                    selectedFilter = selectedFilter
                )
                TransaksiBerhasilSection(
                    navController = navController,
                    transaction = filteredTransaction,
                    percentChange = percentChange,
                    isUp = isUp,
                    prevPeriodLabel = getPrevPeriodLabel(selectedFilter)
                )
                DonutChart(
                    transaction = filteredTransaction,
                    percentChange = percentChange,
                    isUp = isUp,
                    prevPeriodLabel = getPrevPeriodLabel(selectedFilter)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
    if (showOverlay) {
        DateFilterOverlay(
            onDismiss = { showOverlay = false },
            onSelected = { selected ->
                selectedFilter = selected
            }
        )
    }
}

@Composable
private fun TransaksiBerhasilSection(
    navController: NavController,
    transaction: List<Transaction>,
    percentChange: Float,
    isUp: Boolean,
    prevPeriodLabel: String
) {
    val sellList = transaction.filterIsInstance<Transaction.Sell>()
    val purchaseList = transaction.filterIsInstance<Transaction.Purchase>()
    val billList = transaction.filterIsInstance<Transaction.Bill>()

    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .dropShadow200(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .padding(16.dp)
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppText("TRANSAKSI BERHASIL", 14.sp, FontWeight.Bold)
            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { navController.navigate("transaction") }
                )
            ) {
                AppText(
                    text = "Lihat Semua",
                    color = Primary
                )
                Icon(
                    painter = painterResource(R.drawable.ic_next),
                    contentDescription = null,
                    tint = Primary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Box(Modifier.fillMaxWidth().height(1.dp).background(Dark.copy(0.2f)))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppText(
                text = transaction.size.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Column (
                horizontalAlignment = Alignment.End
            ) {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(
                            if (isUp) R.drawable.ic_pendapatan_naik else R.drawable.ic_pendapatan_turun
                        ),
                        contentDescription = null,
                        tint = if (isUp) Success else Danger,
                        modifier = Modifier.size(16.dp)
                    )
                    AppText(
                        text = "${"%.0f".format(percentChange)}%",
                        color = if (isUp) Success else Danger,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
                AppText(
                    text = prevPeriodLabel,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Box(Modifier.fillMaxWidth().height(1.dp).background(Dark.copy(0.2f)))
        Row( Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            AppText("Penjualan")
            AppText(sellList.size.toString())
        }
        Row( Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            AppText("Pembelian")
            AppText(purchaseList.size.toString())
        }
        Row( Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            AppText("Tagihan")
            AppText(billList.size.toString())
        }
    }
}

// Data class untuk chart
data class DonutChartData(val value: Float, val color: Color, val label: String)

@Composable
fun DonutChart(
    transaction: List<Transaction>,
    percentChange: Float,
    isUp: Boolean,
    prevPeriodLabel: String
) {
    val tunai = transaction
        .filterIsInstance<Transaction.Sell>()
        .filter { it.paymentMethod == "Tunai" }
        .sumOf { it.total }

    val qris = transaction
        .filterIsInstance<Transaction.Sell>()
        .filter { it.paymentMethod == "QRIS" }
        .sumOf { it.total }

    val kredit = transaction
        .filterIsInstance<Transaction.Sell>()
        .filter { it.paymentMethod == "Kartu Kredit" }
        .sumOf { it.total }

    val lainnya = transaction
        .filterIsInstance<Transaction.Sell>()
        .filter { it.paymentMethod != "Tunai" && it.paymentMethod != "Kartu Kredit" && it.paymentMethod != "QRIS" }
        .sumOf { it.total }

    val data = listOf(
        DonutChartData(tunai.toFloat(), Primary, "Tunai"),
        DonutChartData(kredit.toFloat(), Primary500, "Kredit"),
        DonutChartData(qris.toFloat(), Primary300, "QRIS"),
        DonutChartData(lainnya.toFloat(), Primary100, "Lainnya")
    )

    val total = data.sumOf { it.value.toDouble() }.toFloat()
    var startAngle = -90f

    val grossIncome = transaction
        .filterIsInstance<Transaction.Sell>()
        .sumOf { it.total }

    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .dropShadow200(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .padding(16.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppText("TOTAL PENDAPATAN KOTOR", 14.sp, FontWeight.Bold)
        }
        DrawLine()
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppText(
                text = formatToCurrency(grossIncome),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Column (
                horizontalAlignment = Alignment.End
            ) {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(
                            if (isUp) R.drawable.ic_pendapatan_naik else R.drawable.ic_pendapatan_turun
                        ),
                        contentDescription = null,
                        tint = if (isUp) Success else Danger,
                        modifier = Modifier.size(16.dp)
                    )
                    AppText(
                        text = "${"%.0f".format(percentChange)}%",
                        color = if (isUp) Success else Danger,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
                AppText(
                    text = prevPeriodLabel,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(152.dp)
                    .aspectRatio(1f)
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ) {
                    data.forEach { item ->
                        val sweepAngle = if (total == 0f) 0f else (item.value / total) * 360f
                        drawArc(
                            color = item.color,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            style = Stroke(width = 100f),
                            size = Size(size.width, size.height)
                        )
                        startAngle += sweepAngle
                    }
                }
            }
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.height(152.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(Modifier.size(16.dp).clip(RoundedCornerShape(4.dp)).background(Primary))
                    AppText("Tunai")
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(Modifier.size(16.dp).clip(RoundedCornerShape(4.dp)).background(Primary500))
                    AppText("Kredit")
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(Modifier.size(16.dp).clip(RoundedCornerShape(4.dp)).background(Primary300))
                    AppText("QRIS")
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(Modifier.size(16.dp).clip(RoundedCornerShape(4.dp)).background(Primary100))
                    AppText("Lainnya")
                }
            }
        }
        DrawLine()
        Row( Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            AppText("Tunai")
            AppText(formatToCurrency(tunai))
        }
        Row( Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            AppText("QRIS")
            AppText(formatToCurrency(qris))
        }
        Row( Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            AppText("Kredit")
            AppText(formatToCurrency(kredit))
        }
        Row( Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            AppText("Lainnya")
            AppText(formatToCurrency(lainnya))
        }
    }
}
