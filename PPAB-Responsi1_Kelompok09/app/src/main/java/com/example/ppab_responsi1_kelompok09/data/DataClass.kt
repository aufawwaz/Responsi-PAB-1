package com.example.ppab_responsi1_kelompok09.data

data class NavItem(
    val route: String,
    val icon: Int,
    val selectedIcon: Int
)

data class MenuItem(
    val label : String,
    val icon : Int
)

data class KnowledgeCardItem(
    val imageRes : Int,
    val title : String,
    val description : String
)

data class TabelItem(
    val imageRes : Int,
    val name : String,
    val date: String,
    val money : String
)

data class ProductItem(
    val onCLick : () -> Unit,
    val productImage : Int,
    val category : String,
    val productName : String,
    val sold : String,
    val stock : String,
    val price : String
)

data class Contact(
    val imageRes : Int,
    val name : String,
    val number : String
)

sealed class Transaction{
    data class Sell(
        val id : String,
        val customer : String,
        val date : String,
        val paymentMethod : String,
        val total : String
     ) : Transaction()

    data class Purchase(
        val id : String,
        val seller : String,
        val date : String,
        val total : String
    ) : Transaction()

    data class Bill(
        val id : String,
        val customer : String,
        val date : String,
        val status : String,
        val total : String
    ) : Transaction()
}
