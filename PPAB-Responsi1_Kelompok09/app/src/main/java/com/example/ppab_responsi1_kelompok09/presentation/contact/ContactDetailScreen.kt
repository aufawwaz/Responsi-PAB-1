package com.example.ppab_responsi1_kelompok09.presentation.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ppab_responsi1_kelompok09.R
import com.example.ppab_responsi1_kelompok09.domain.model.Contact
import com.example.ppab_responsi1_kelompok09.domain.repository.ContactRepository
import com.example.ppab_responsi1_kelompok09.presentation.components.AppText
import com.example.ppab_responsi1_kelompok09.presentation.components.CustomSwitch
import com.example.ppab_responsi1_kelompok09.presentation.components.HeaderPageOnBack
import com.example.ppab_responsi1_kelompok09.presentation.components.TopSpacer
import com.example.ppab_responsi1_kelompok09.ui.theme.Dark
import com.example.ppab_responsi1_kelompok09.ui.theme.White

@Composable
fun ContactDetailScreen (
    navController: NavController,
    contactId : String
) {
    val contact = remember { ContactRepository.getContactById(contactId) }

    if (contact == null) {
        // Bisa tampilkan error atau loading
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {}
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(vertical = 20.dp),
        ) {
            Column (
                modifier = Modifier.fillMaxWidth()
            ) {
                HeaderPageOnBack(
                    onClick = { navController.popBackStack() },
                    text = "Detail Kontak"
                )
                Column (
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(Modifier.height(1.dp))
                    ImageNameSection(contact)

                    var isChecked by rememberSaveable { mutableStateOf(false) }
                    CustomSwitch(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it },
                        text1 = "Detail",
                        text2 = "Transaksi"
                    )

                    Spacer(Modifier.height(0.dp))

                    if (!isChecked) ContactDescription(contact)
                    else ContactActivity(contact)
                }
            }
        }
    }
}


@Composable
private fun ImageNameSection(contact: Contact) {
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(contact.image_kontak),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp),
            contentScale = ContentScale.Crop
        )
        AppText(
            text = contact.nama_kontak,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Box(Modifier.fillMaxWidth().height(1.dp).background(Dark.copy(0.2f)))
    }
}

@Composable
private fun ContactDescription(contact: Contact) {
    data class ContactDescriptionItem (
        val icon: Int,
        val text: String
    )

    val descriptionList = listOf(
        ContactDescriptionItem(R.drawable.ic_phone, contact.nomor_kontak),
        ContactDescriptionItem(R.drawable.ic_email, contact.email_kontak),
        ContactDescriptionItem(R.drawable.ic_location, contact.alamat_kontak),
    )

    LazyColumn (
        userScrollEnabled = false,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(descriptionList.size) { index ->
            val item = descriptionList[index]
            Row (
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = null,
                    tint = Dark,
                    modifier = Modifier.size(20.dp)
                )
                AppText(
                    text = item.text,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun ContactActivity(contact: Contact) {

}