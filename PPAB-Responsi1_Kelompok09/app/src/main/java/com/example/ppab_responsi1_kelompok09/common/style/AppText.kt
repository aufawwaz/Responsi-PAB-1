package com.example.ppab_responsi1_kelompok09.common.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.ppab_responsi1_kelompok09.ui.theme.Dark
import com.example.ppab_responsi1_kelompok09.ui.theme.Poppins
import com.example.ppab_responsi1_kelompok09.ui.theme.White

// Kalau mau bikin teks pake ini
// Fontnya udah poppins, lineheight sama letterspacing udah direset

@Composable
fun AppText(
    text: String,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Dark,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        letterSpacing = 0.sp,
        lineHeight = fontSize,
        fontFamily = Poppins,
        color = color,
        modifier = modifier
    )
}

@Composable
fun HomeTextHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    AppText(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun PageTextHeader (
    text: String,
    modifier: Modifier = Modifier
) {
    AppText(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = White,
        modifier = modifier
    )
}