package com.sadique.bank.controller.dto


data class TransactionDto(
    val id: Long?,
    val type: String,
    val amount: Int,
    val recipient: String
)

