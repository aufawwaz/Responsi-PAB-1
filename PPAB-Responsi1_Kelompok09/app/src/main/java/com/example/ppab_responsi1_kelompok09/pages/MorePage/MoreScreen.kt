package com.example.ppab_responsi1_kelompok09.pages.MorePage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ppab_responsi1_kelompok09.R
import com.example.ppab_responsi1_kelompok09.component.HeaderGradient
import com.example.ppab_responsi1_kelompok09.component.ProfileContainer
import com.example.ppab_responsi1_kelompok09.ui.theme.Danger
import com.example.ppab_responsi1_kelompok09.ui.theme.Gray
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary

@Composable
fun MoreScreen () {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HeaderGradient()
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileContainer(
                R.drawable.login,
                "Biru",
                true
            )
            ManajemenItem(
                true,
                "",
                R.drawable.pelanggan_fill
            )
            Text(
                text = "Manajemen",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            ManajemenItemContainer()
            Logout()
        }
    }
}

@Composable
fun ManajemenItemContainer () {
    ManajemenItem(false, "Profil Bisnis", R.drawable.home_fill)
    ManajemenItem(false, "Kelola Saldo", R.drawable.home_fill)
    ManajemenItem(false, "Lihat Laporan Keuangan", R.drawable.home_fill)
}

@Composable
fun ManajemenItem (
    isAkun : Boolean = false,
    text : String,
    icon : Int
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Primary.copy(0.1f))
            ) {
                Icon(
//                            Perlu diganti login fill
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    tint = Primary
                )
            }
            if (isAkun == true) {
                Text(
                    text = "Akun saya",
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            } else {
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
        }
        Icon(
            painter = painterResource(R.drawable.next),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp),
            tint = Primary
        )
    }
}

@Composable
fun Logout () {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Danger.copy(0.1f))
            ) {
                Icon(
                    painter = painterResource(R.drawable.previous),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    tint = Danger
                )
            }
            Text(
                text = "Logout",
                color = Danger,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
        Icon(
            painter = painterResource(R.drawable.next),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp),
            tint = Danger
        )
    }
}