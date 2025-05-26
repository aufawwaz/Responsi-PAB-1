package com.example.ppab_responsi1_kelompok09.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ppab_responsi1_kelompok09.R
import com.example.ppab_responsi1_kelompok09.common.style.PageTextHeader

@Composable
fun PageHeader(title : String, iconRes : Int, description : String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(162.dp)
    ) {
        HeaderGradient()
        PageTextHeader(
            text = "Kontak",
            modifier = Modifier.offset(x = 16.dp, y = 56.dp)
        )
        HeaderBox(
            iconRes = R.drawable.pelanggan_fill,
            title = "Total Kontak",
            description = "5 Kontak",
            modifier = Modifier
                .offset(y = 102.dp)
        )
    }
}