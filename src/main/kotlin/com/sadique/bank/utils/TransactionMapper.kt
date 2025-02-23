package com.sadique.bank.utils

import com.sadique.bank.controller.dto.TransactionDto
import com.sadique.bank.repository.model.TransactionDbModel

// file to help us convert from one form to another
//map the payload values to the corresponding db fields

object TransactionMapper {
    fun toEntity(dto: TransactionDto): TransactionDbModel {
        val model = TransactionDbModel()
//        map the fields in TransactionModel to TransactionDto
        model.type = dto.type
        model.amount = dto.amount
        model.recipient = dto.recipient

        return model
    }

}

