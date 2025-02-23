package com.sadique.bank.controller.dto

data class ApiResponseDto<T>(
    val message: String,
    val code: Int,
    val body: T?
)