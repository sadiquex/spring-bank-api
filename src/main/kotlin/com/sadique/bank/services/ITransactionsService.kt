package com.sadique.bank.services

import com.sadique.bank.controller.dto.TransactionDto

interface ITransactionsService {
    fun addTransaction(dto: TransactionDto): TransactionDto?
}