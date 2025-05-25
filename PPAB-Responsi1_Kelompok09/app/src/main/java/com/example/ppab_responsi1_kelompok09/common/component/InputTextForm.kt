package com.example.ppab_responsi1_kelompok09.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ppab_responsi1_kelompok09.common.style.AppText
import com.example.ppab_responsi1_kelompok09.ui.theme.Gray

// Component input form yang ada di register dan login screen

@Composable
fun InputTextForm(text: String, icon: Int, isPassword: Boolean) {
    var inputValue by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Gray, RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .height(IntrinsicSize.Min),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Gray,
                modifier = Modifier.width(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = inputValue,
                onValueChange = { inputValue = it },
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                decorationBox = { innerTextField ->
                    if (inputValue.isEmpty()) {
                        AppText(text, 12.sp, color = Gray)
                    }
                    innerTextField()
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}