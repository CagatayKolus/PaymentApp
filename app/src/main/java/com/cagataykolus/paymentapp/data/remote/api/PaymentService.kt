package com.cagataykolus.paymentapp.data.remote.api

import com.cagataykolus.paymentapp.data.remote.api.PaymentService.Companion.PAYMENT_API_URL
import com.cagataykolus.paymentapp.model.Payment
import com.cagataykolus.paymentapp.model.PaymentResult
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
/**
 * Service to fetch data using endpoint [PAYMENT_API_URL].
 */
interface PaymentService {
    @GET("listresult.json")
    suspend fun getPaymentResult(): Response<PaymentResult>

    companion object {
        const val PAYMENT_API_URL = "https://raw.githubusercontent.com/optile/checkout-android/develop/shared-test/lists/"
    }
}