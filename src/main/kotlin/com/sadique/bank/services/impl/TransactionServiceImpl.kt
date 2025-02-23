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
}