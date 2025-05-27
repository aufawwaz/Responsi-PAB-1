@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ppab_responsi1_kelompok09.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ppab_responsi1_kelompok09.R
import com.example.ppab_responsi1_kelompok09.common.component.CustomButton
import com.example.ppab_responsi1_kelompok09.common.component.CustomCheckbox
import com.example.ppab_responsi1_kelompok09.common.component.HorizontalLine
import com.example.ppab_responsi1_kelompok09.common.component.InputTextForm
import com.example.ppab_responsi1_kelompok09.common.component.ScaleUpFullLogo
import com.example.ppab_responsi1_kelompok09.common.style.AppText
import com.example.ppab_responsi1_kelompok09.ui.theme.Gray
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary
import com.example.ppab_responsi1_kelompok09.ui.theme.Primary900
import com.example.ppab_responsi1_kelompok09.ui.theme.White

@Composable
fun LoginScreen(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize().background(Primary900)
    ){
        Box(
            modifier = Modifier
                .background(brush = Brush.linearGradient(
                    listOf(Primary900 ,Primary),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(0f, -5f)
                ))
                .fillMaxWidth()
                .height(240.dp)
        ) {
            ScaleUpFullLogo( modifier = Modifier.align(Alignment.Center) )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(630.dp)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(White)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(Modifier.height(24.dp))
            AppText("Login", 18.sp, FontWeight.W600)
            Spacer(Modifier.height(24.dp))

            Spacer(Modifier.height(16.dp))
            Column (
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 56.dp, end = 56.dp)
            ) {
                LoginForm(navController)
            }
        }
    }
}

@Composable
private fun LoginForm(navController: NavController){

    // SIGN IN GOOGLE
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Gray, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(White, RoundedCornerShape(12.dp))
            .clickable { /* sign in google */ }
            .padding(horizontal = 12.dp)
            .height(44.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_google_logo),
                contentDescription = null,
                modifier = Modifier.width(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            AppText(
                "Sign in with Google",
                12.sp,
                FontWeight.Bold
            )
        }
    }
    HorizontalLine()
    InputTextForm("Email", R.drawable.ic_email, false)
    InputTextForm("Password", R.drawable.ic_password, true)
    InputTextForm("Confirm Password", R.drawable.ic_password, true)

    // LOGIN CONFIRMATION
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                CustomCheckbox(15.sp)
                Spacer(Modifier.width(7.dp))
                AppText("Remember me", 11.sp)
            }
            AppText("Forgot Password?", 11.sp, color = Primary)
        }
        CustomButton(
            { navController.navigate("main") },
            "Login"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .padding(bottom = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AppText("Don't have account? ", 11.sp)
            AppText(
                "Create account",
                11.sp,
                color = Primary,
                modifier = Modifier.clickable{ navController.navigate("register") }
            )
        }
    }
}