package com.sadique.bank.controller

import com.sadique.bank.controller.dto.ApiResponseDto
import com.sadique.bank.controller.dto.TransactionDto
import com.sadique.bank.services.ITransactionsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionsController(val transactionService: ITransactionsService) { // 1. reference what service we're using
    @GetMapping("/getAll")
    fun getAllTransactions(): String {
        return "All transactions"
    }

    @PostMapping("/add")
    fun addTransaction(@RequestBody payload: TransactionDto): ResponseEntity<ApiResponseDto<TransactionDto>> {
        val addTransactionResponse = transactionService.addTransaction(payload)

        val code = if (addTransactionResponse != null) 200 else 400
        val message = if (addTransactionResponse != null) "Transaction added successfully" else "Transaction failed"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = addTransactionResponse
        )

        return ResponseEntity.status(code).body(apiResponse)
    }

}