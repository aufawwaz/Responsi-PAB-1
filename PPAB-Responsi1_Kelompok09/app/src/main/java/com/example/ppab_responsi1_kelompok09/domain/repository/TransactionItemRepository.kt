package com.example.ppab_responsi1_kelompok09.domain.repository

import com.example.ppab_responsi1_kelompok09.domain.model.TransactionItem

object TransactionItemRepository {
    private val allData = listOf(
//        DUMMY DATAS
        TransactionItem("TRANSACTION_ID", "PRD001", 2),
        TransactionItem("TRANSACTION_ID", "PRD002", 2),
        TransactionItem("TRANSACTION_ID", "PRD003", 2),
        TransactionItem("TRANSACTION_ID", "PRD004", 2),
        TransactionItem("TRANSACTION_ID", "PRD005", 2),
        TransactionItem("TRANSACTION_ID", "PRD006", 2),
    )

    fun getTransactionItems(id: String): List<TransactionItem> {
//        return allData.filter { it.transactionId == id }
        return allData // Pake dummy data dulu
    }

}