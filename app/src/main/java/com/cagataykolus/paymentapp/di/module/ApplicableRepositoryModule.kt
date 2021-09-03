package com.cagataykolus.paymentapp.di.module

import com.cagataykolus.paymentapp.data.repository.DefaultApplicableRepository
import com.cagataykolus.paymentapp.data.repository.ApplicableRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ApplicableRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindApplicableRepository(repository: DefaultApplicableRepository): ApplicableRepository
}