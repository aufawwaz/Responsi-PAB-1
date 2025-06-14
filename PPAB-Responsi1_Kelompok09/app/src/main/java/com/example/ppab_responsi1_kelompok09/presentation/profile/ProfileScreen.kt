package com.example.ppab_responsi1_kelompok09.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ppab_responsi1_kelompok09.R
import com.example.ppab_responsi1_kelompok09.domain.model.User
import com.example.ppab_responsi1_kelompok09.domain.repository.UserRepository
import com.example.ppab_responsi1_kelompok09.presentation.components.TopSpacer
import com.example.ppab_responsi1_kelompok09.presentation.components.dropShadow200
import com.example.ppab_responsi1_kelompok09.ui.theme.Dark
import com.example.ppab_responsi1_kelompok09.ui.theme.White

@Composable
fun ProfileScreen ( navController: NavController) {

    val user = UserRepository.adminUser

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ImageBox(user, navController)
        }
    }

}

@Composable
private fun ImageBox(user: User, navController: NavController) {
    Box (
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(user.profilePhoto?: R.drawable.img_profile_picture),
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