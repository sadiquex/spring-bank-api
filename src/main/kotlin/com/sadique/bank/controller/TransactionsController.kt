package com.sadique.bank.controller

import com.sadique.bank.controller.dto.ApiResponseDto
import com.sadique.bank.controller.dto.TransactionDto
import com.sadique.bank.services.ITransactionsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionsController(val transactionService: ITransactionsService) { // 1. reference what service we're using
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

    @GetMapping("/allTransactions")
    fun getAllTransactions(): ResponseEntity<ApiResponseDto<List<TransactionDto>>> {
        val allTransactions = transactionService.getAllTransactions()

        val isSuccess = allTransactions.isNotEmpty()
        val code = if (isSuccess) 200 else 400
        val message = if (isSuccess) "Transaction added successfully" else "Failed to get transactions"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = allTransactions
        )
        return ResponseEntity.status(200).body(apiResponse)
    }

    @GetMapping("/{id}")
//    1. extract the id from the URL
    fun getTransactionById(@PathVariable id: Long): ResponseEntity<ApiResponseDto<TransactionDto>> {
//     2. call the transactionService (worker) to find the transaction by ID.
        val transaction = transactionService.getTransactionById(id)
//        3. set the status code
        val code = if (transaction != null) 200 else 400
        val message = if (transaction != null) "Transaction found" else "Transaction with id $id not found"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = transaction
        )

//        4. check if the transaction is found and handle it
        return ResponseEntity.status(code).body(apiResponse)
    }

    @PutMapping("/{id}")
    fun updateTransaction(
        @PathVariable id: Long,
        @RequestBody transactionDto: TransactionDto
    ): ResponseEntity<ApiResponseDto<TransactionDto>> {
//        call the updateTransaction service
        val updatedTransaction = transactionService.updateTransaction(id, transactionDto)
//        set the status code
        val code = if (updatedTransaction != null) 200 else 400
        val message = if (updatedTransaction != null) "Transaction updated" else "Failed to update"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = updatedTransaction
        )
        return ResponseEntity.status(code).body(apiResponse)

    }

    @DeleteMapping("/{id}")
    fun deleteTransaction(
        @PathVariable id: Long
    ): ResponseEntity<ApiResponseDto<TransactionDto>> {
//        call the deleteTransaction service
        val deletedTransaction = transactionService.deleteTransaction(id)
//        set the status code
        val code = if (deletedTransaction != null) 200 else 400
        val message =
            if (deletedTransaction != null) "Transaction deleted" else "Failed to delete transaction with id $id"

        val apiResponse = ApiResponseDto(
            message = message,
            code = code,
            body = deletedTransaction
        )
        return ResponseEntity.status(code).body(apiResponse)

    }

}