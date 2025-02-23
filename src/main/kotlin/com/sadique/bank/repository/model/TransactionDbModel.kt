package com.sadique.bank.repository.model

import jakarta.persistence.*

@Entity
@Table(name = "Transactions")
class TransactionDbModel {
    //    EVERY TABLE NEEDS A PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generate id for our table
    var id: Long? = null

    //    fields we'll have in our Transactions Table
    var type: String = ""
    var amount: Int = 0
    var recipient: String = ""

}