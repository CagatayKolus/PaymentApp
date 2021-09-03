package com.cagataykolus.paymentapp.model

data class Payment(
    val amount: Int,
    val currency: String,
    val reference: String
)