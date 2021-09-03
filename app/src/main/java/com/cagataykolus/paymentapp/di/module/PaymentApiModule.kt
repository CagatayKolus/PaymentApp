package com.cagataykolus.paymentapp.di.module

import com.cagataykolus.paymentapp.data.remote.api.PaymentService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class PaymentApiModule {
    @Singleton
    @Provides
    fun provideRetrofitService(): PaymentService = Retrofit.Builder()
        .baseUrl(PaymentService.PAYMENT_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(PaymentService::class.java)
}
