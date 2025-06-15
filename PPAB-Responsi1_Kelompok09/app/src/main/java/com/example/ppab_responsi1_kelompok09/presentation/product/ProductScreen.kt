package com.example.ppab_responsi1_kelompok09.presentation.product

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ppab_responsi1_kelompok09.R
import com.example.ppab_responsi1_kelompok09.domain.model.Product
import com.example.ppab_responsi1_kelompok09.domain.repository.ProductRepository
import com.example.ppab_responsi1_kelompok09.presentation.components.BottomSpacer
import com.example.ppab_responsi1_kelompok09.presentation.components.CustomButton
import com.example.ppab_responsi1_kelompok09.presentation.components.PageHeader
import com.example.ppab_responsi1_kelompok09.presentation.components.ProductCard
import com.example.ppab_responsi1_kelompok09.presentation.components.SearchBarFilter
import com.example.ppab_responsi1_kelompok09.presentation.components.AppText
import com.example.ppab_responsi1_kelompok09.ui.theme.Dark
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary
import com.example.ppab_responsi1_kelompok09.ui.theme.White
import java.math.BigDecimal

@Composable
fun ProductScreen(navController: NavController = rememberNavController()) {
    val product = ProductRepository.getAllProducts()
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val filteredProducts = if (searchQuery.isBlank()) {
        product
    } else {
        product.filter { it.productName.contains(searchQuery, ignoreCase = true) }
    }
    val listState = rememberLazyListState()
    val isSticky = listState.firstVisibleItemIndex > 0
    Box (
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(White),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                PageHeader(
                    pagetitle = "Produk",
                    title = "Total Produk",
                    iconRes = R.drawable.ic_produk_fill,
                    description = filteredProducts.size.toString() + " Produk"
                )
                KategoriSatuanSection(navController)
                SearchBarFilter(Modifier.padding(top = 16.dp), "Cari Produk", onSearch = { searchQuery = it })
            }
            itemsIndexed(filteredProducts.chunked(2)) { _, rowItems ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    for ((index, item) in rowItems.withIndex()) {
                        ProductCard(
                            onCLick = { navController.navigate("product_detail/${item.id}") },
                            productImage = item.productImage,
                            category = item.category,
                            productName = item.productName,
                            sold = item.sold,
                            stock = item.stock,
                            price = item.price,
                            modifier = Modifier.weight(1f)
                        )
                        if (index == 0 && rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
            item { BottomSpacer() }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = -(12.dp), y = -(138.dp))
                .width(180.dp)
        ) {
            CustomButton({ }, {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_box),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    AppText("Tambah Produk", 12.sp, color = White)
                }
            })
        }
    }
}

@Composable
private fun KategoriSatuanSection(navController: NavController) {
    Row (
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        KategoriSatuanItem(
            navController,
            modifier = Modifier.weight(1f),
            iconRes = R.drawable.ic_kategori_fill,
            text = "Kategori"
        )
        KategoriSatuanItem(
            navController,
            modifier = Modifier.weight(1f),
            iconRes = R.drawable.ic_satuan_fill,
            text = "Satuan"
        )
    }
}

@Composable
private fun KategoriSatuanItem (
    navController: NavController,
    modifier: Modifier = Modifier,
    iconRes : Int,
    text : String
) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Primary.copy(0.1f))
            .height(44.dp)
            .padding(horizontal = 16.dp)
            .clickable {}
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon (
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(12.dp)
            )
            AppText(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Primary
            )
        }
        Icon (
            painter = painterResource(R.drawable.ic_next),
            contentDescription = null,
            tint = Primary,
            modifier = Modifier.size(12.dp)
        )
    }
}
