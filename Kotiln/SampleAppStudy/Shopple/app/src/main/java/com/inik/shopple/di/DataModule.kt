package com.inik.shopple.di

import com.inik.data.repository.MainRepositoryImpl
import com.inik.data.repository.TempRepositoryImpl
import com.inik.domain.repository.MainRepository
import com.inik.domain.repository.TempRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTempRepository(tempRepositoryImpl: TempRepositoryImpl): TempRepository

    @Binds
    @Singleton
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}