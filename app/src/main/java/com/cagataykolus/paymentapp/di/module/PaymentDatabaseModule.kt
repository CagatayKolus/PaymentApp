package com.cagataykolus.paymentapp.di.module

import android.app.Application
import com.cagataykolus.paymentapp.data.local.ApplicableDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class PaymentDatabaseModule {
    @Singleton
    @Provides
    fun provideApplicableDatabase(application: Application) = ApplicableDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideApplicableDao(database: ApplicableDatabase) = database.getApplicableDao()
}