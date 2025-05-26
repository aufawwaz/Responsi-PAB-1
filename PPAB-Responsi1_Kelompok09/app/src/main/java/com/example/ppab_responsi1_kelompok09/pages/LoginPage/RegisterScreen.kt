package com.example.ppab_responsi1_kelompok09.pages.LoginPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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

// @Preview(showBackground = true)
@Composable
fun RegisterScreen(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize().background(Primary900)
    ){
        Box(
            modifier = Modifier
                .background(brush = Brush.linearGradient(
                    listOf(Primary900 , Primary),
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
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(Modifier.height(24.dp))
            AppText("Register", 18.sp, FontWeight.W600, MaterialTheme.colorScheme.onBackground)
            Spacer(Modifier.height(24.dp))

            RegisterForm(navController)
        }
    }
}

@Composable
private fun RegisterForm(navController: NavController){
    Spacer(Modifier.height(16.dp))
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 56.dp, end = 56.dp)
    ) {
        // SIGN IN GOOGLE
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Gray, RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp)
                .height(44.dp)
                .clickable { /* sign in google */ },
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = null,
                    modifier = Modifier.width(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                AppText(
                    "Sign in with Google",
                    12.sp,
                    FontWeight.Bold,
                    MaterialTheme.colorScheme.onBackground
                )
            }
        }
        HorizontalLine()
        InputTextForm("Username", R.drawable.login, false)
        InputTextForm("Email", R.drawable.email, false)
        InputTextForm("Password", R.drawable.password, true)

        // REGISTER CONFIRMATION
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                CustomCheckbox(15.sp)
                Spacer(Modifier.width(7.dp))
                AppText("I agree to the ", 11.sp)
                AppText("Terms & Conditions", 11.sp, color = Primary)
            }
            CustomButton({ navController.navigate("login") }, "Sign Up")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .padding(bottom = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                AppText("Already have an account? ", 11.sp)
                AppText(
                    "Sign In",
                    11.sp,
                    color = Primary,
                    modifier = Modifier.clickable{ navController.navigate("login") }
                )
            }
        }
    }
}