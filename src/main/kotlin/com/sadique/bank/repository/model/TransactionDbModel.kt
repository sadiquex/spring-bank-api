package com.sadique.bank.repository.model

import jakarta.persistence.*

// this represents the database table structure.
@Entity // marks this class as a database entity
@Table(name = "Transactions") // specifies the database table
class TransactionDbModel {
    //    EVERY TABLE NEEDS A PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generate id for our table (generate key with auto-increment)
    var id: Long? = null

    //    fields we'll have in our Transactions Table
    var type: String = ""
    var amount: Int = 0
    var recipient: String = ""

}