package com.inik.shopping.di

import com.inik.shopping.remote.MainService
import com.inik.shopping.remote.repository.MainRepository
import com.inik.shopping.remote.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.android.scopes.ViewScoped

@Module
@InstallIn(ViewModelComponent::class)
object MainRepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesManRepository(
        mainService: MainService
    ): MainRepository = MainRepositoryImpl(mainService)
}