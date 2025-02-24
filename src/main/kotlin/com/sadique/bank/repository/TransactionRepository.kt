package com.sadique.bank.repository

import com.sadique.bank.repository.model.TransactionDbModel
import org.springframework.data.jpa.repository.JpaRepository

// contains methods for performing CRUD operations to db

// state what type of repository we're using
interface TransactionRepository : JpaRepository<TransactionDbModel, Long>  // typeof dbModel and typeof PrimaryKey
