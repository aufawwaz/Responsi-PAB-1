package com.example.ppab_responsi1_kelompok09.presentation.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ppab_responsi1_kelompok09.R
import com.example.ppab_responsi1_kelompok09.domain.model.Product
import com.example.ppab_responsi1_kelompok09.domain.repository.ProductRepository
import com.example.ppab_responsi1_kelompok09.presentation.components.AppText
import com.example.ppab_responsi1_kelompok09.presentation.components.CustomSwitch
import com.example.ppab_responsi1_kelompok09.presentation.components.TopSpacer
import com.example.ppab_responsi1_kelompok09.presentation.components.dropShadow200
import com.example.ppab_responsi1_kelompok09.presentation.components.formatToCurrency
import com.example.ppab_responsi1_kelompok09.ui.theme.Dark
import com.example.ppab_responsi1_kelompok09.ui.theme.Success
import com.example.ppab_responsi1_kelompok09.ui.theme.White

@Composable
fun ProductDetailScreen (
    productId: String,
    navController: NavController
) {
    val product = remember { ProductRepository.getProductById(productId) }

    if (product == null) {
        // Bisa tampilkan error atau loading
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {}
    } else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column (
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ImageBox(navController, product)

                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    AppText(
                        text = product.productName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(24.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Dark.copy(0.09f))
                            .padding(horizontal = 8.dp)
                    ) {
                        AppText(
                            text = product.category,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp
                        )
                    }
                }

                var isChecked by rememberSaveable { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    CustomSwitch(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it },
                        text1 = "Detail",
                        text2 = "Aktivitas"
                    )
                }

                if(!isChecked) ProductDescription(product)
                else ProductActivity(product)
            }
        }
    }
}

@Composable
private fun ImageBox (
    navController: NavController,
    product: Product
) {
    Box (
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(product.productImage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
            contentScale = ContentScale.Crop
        )
        Column (
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            TopSpacer()
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .dropShadow200(999.dp)
                        .background(White)
                        .clickable{ navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter =  painterResource(R.drawable.ic_previous),
                        contentDescription = null,
                        tint = Dark,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .dropShadow200(999.dp)
                        .background(White)
                        .clickable{ navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter =  painterResource(R.drawable.ic_titik_tiga),
                        contentDescription = null,
                        tint = Dark,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductDescription(
    product: Product
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AppText(
                text = product.productDescription,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 14.sp
            )
        }
        AppText(
            text = formatToCurrency(product.price) + "/" + product.satuan,
            color = Success,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Box(Modifier.fillMaxWidth().height(1.dp).background(Dark.copy(0.2f)))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppText(
                text = "Stok Tersedia",
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            AppText(
                text = product.stock.toString(),
                fontWeight = FontWeight.SemiBold
            )
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppText(
                text = "Terjual",
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            AppText(
                text = product.sold.toString() + " " + product.satuan,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppText(
                text = "Harga Modal",
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            AppText(
                text = formatToCurrency(product.modal),
                fontWeight = FontWeight.SemiBold
            )
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppText(
                text = "Keuntungan",
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            val profit = product.price - product.modal
            AppText(
                text = formatToCurrency(profit) + "/Penjualan",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun ProductActivity(
    product: Product
) {

}