package com.sadique.bank.repository

import com.sadique.bank.repository.model.TransactionDbModel
import org.springframework.data.jpa.repository.JpaRepository

// file for setting up our repository
// a repository takes care of our db transactions

// state what type of repository we're using
interface TransactionRepository : JpaRepository<TransactionDbModel, Long>  // typeof dbModel and typeof PrimaryKey