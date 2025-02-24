package com.sadique.bank.services.impl

import com.sadique.bank.controller.dto.TransactionDto
import com.sadique.bank.repository.TransactionRepository
import com.sadique.bank.services.ITransactionsService
import com.sadique.bank.utils.TransactionMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl(val repository: TransactionRepository) : ITransactionsService {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun addTransaction(dto: TransactionDto): TransactionDto? {
//        logging convention: ServiceName :: FunctionName :: WhatItsDoing :: Payload
        logger.info("TransactionServiceImpl :: addTransaction :: adding a transaction :: $dto")

        try {
            //        map dto to our TransactionDbModel
            val mappedData = TransactionMapper.toEntity(dto)

//        add mapper to our repository
            repository.save(mappedData)
            logger.info("TransactionServiceImpl :: addTransaction :: transaction added successfully :: $mappedData")

//        return dto to client
            return dto
        } catch (e: Exception) {
            logger.info("TransactionServiceImpl :: addTransaction :: An exception occurred :: $e")
            return null
        }


    }

    override fun getTransactionById(id: Long): TransactionDto? {
        logger.info("TransactionServiceImpl :: getTransactionById :: fetching a transaction :: $id")

        try {
            val transaction = repository.findById(id)

            if (transaction.isPresent) {
                val transactionEntity = transaction.get()
                logger.info("TransactionServiceImpl :: getTransactionById :: transaction found :: $transactionEntity")

                // convert entity back to DTO and return
                val convertedToTransaction = TransactionMapper.toTransaction((transactionEntity))
                return convertedToTransaction
            } else {
                logger.info("TransactionServiceImpl :: getTransactionById :: transaction not found with ID :: $id")
                return null
            }


        } catch (e: Exception) {
            logger.error("TransactionServiceImpl :: getTransactionById :: An exception occurred :: $e")
            return null
        }

    }

    override fun getAllTransactions(): List<TransactionDto> {
        logger.info("TransactionServiceImpl :: getAllTransactions :: fetching all transactions")

        try {
            val allTransactions = repository.findAll()
            return allTransactions.map { TransactionMapper.toTransaction(it) } // convert to tranaction
        } catch (e: Exception) {
            logger.error("TransactionServiceImpl :: getAllTransactions :: An exception occurred :: $e")
            return emptyList()
        }
    }

    //    we take id because it identifies which transaction we want to update
//    the transactionDto contains the data sent from the client
    override fun updateTransaction(id: Long, transactionDto: TransactionDto): TransactionDto? {
        logger.info("TransactionServiceImpl :: updateTransaction :: updating transaction :: $id")

        try {
            val existingTransaction = repository.findById(id) // use the id to find the transaction
            if (existingTransaction.isEmpty) return null // transaction not found

            val transactionToUpdate = existingTransaction.get().apply {
//            update the existingTransaction with the data we're receiving from client (transactionDto)
                type = transactionDto.type
                amount = transactionDto.amount
                recipient = transactionDto.recipient
            }
            val savedTransaction = repository.save(transactionToUpdate)
//        convert entity back to dto and return it
            return TransactionMapper.toTransaction((savedTransaction))
        } catch (e: Exception) {
            logger.error("TransactionServiceImpl :: updateTransaction :: An exception occurred :: $e")
            return null
        }

    }

    override fun deleteTransaction(id: Long): TransactionDto? {
        logger.info("TransactionServiceImpl :: deleteTransaction :: deleting a transaction :: $id")

        try {
            val transaction = repository.findById(id)
            if (transaction.isPresent) {
//                find the transaction entity
                val transactionEntity = transaction.get()

                // convert the transaction entity to DTO before deleting
                val deletedTransactionDto = TransactionMapper.toTransaction(transactionEntity)

                // delete the transaction from the database
                repository.delete(transactionEntity)

                logger.info("TransactionServiceImpl :: deleteTransaction :: transaction deleted :: $deletedTransactionDto")
//                return details of deleted transaction
                return deletedTransactionDto

            } else {
                logger.info("TransactionServiceImpl :: deleteTransaction :: transaction not found with ID :: $id")
                return null
            }

        } catch (e: Exception) {
            logger.error("TransactionServiceImpl :: deleteTransaction :: An exception occurred :: $e")
            return null
        }


    }
}