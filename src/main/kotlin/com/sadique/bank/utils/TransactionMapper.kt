package com.sadique.bank.utils

import com.sadique.bank.controller.dto.TransactionDto
import com.sadique.bank.repository.model.TransactionDbModel

// file to help us convert from one form to another
//map the payload values to the corresponding db fields

object TransactionMapper {
    //    convert from TransactionDto -> TransactionDbModel (for saving in db)
    fun toEntity(dto: TransactionDto): TransactionDbModel {
        val model = TransactionDbModel()
//        map the fields in TransactionModel to TransactionDto
        model.type = dto.type
        model.amount = dto.amount
        model.recipient = dto.recipient

        return model
    }

    // convert TransactionDbModel -> TransactionDto (for returning to client)
    fun toTransaction(model: TransactionDbModel): TransactionDto {
        return TransactionDto(
            id = model.id,
            type = model.type,
            amount = model.amount,
            recipient = model.recipient
        )
    }


}

