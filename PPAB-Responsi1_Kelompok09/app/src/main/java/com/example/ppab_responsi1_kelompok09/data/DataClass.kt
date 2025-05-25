package com.example.ppab_responsi1_kelompok09.data

import java.time.LocalDate

data class DataClass(
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