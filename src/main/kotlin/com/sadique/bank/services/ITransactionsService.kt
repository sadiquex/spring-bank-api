package com.sadique.bank.services

import com.sadique.bank.controller.dto.TransactionDto

// interface with abstract method
interface ITransactionsService {
    fun addTransaction(dto: TransactionDto): TransactionDto?

    fun getTransactionById(id: Long): TransactionDto?

    fun getAllTransactions(): List<TransactionDto>

    fun updateTransaction(id: Long, transactionDto: TransactionDto): TransactionDto?

    fun deleteTransaction(id: Long): TransactionDto?

}