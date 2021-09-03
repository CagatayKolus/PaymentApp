package com.cagataykolus.paymentapp.model

import androidx.room.TypeConverter
import androidx.room.TypeConverters

data class ContractData(
    val JAVASCRIPT_INTEGRATION: String,
    val PAGE_BUTTON_LOCALE: String,
    val PAGE_ENVIRONMENT: String
)