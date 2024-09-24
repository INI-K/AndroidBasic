package com.inik.blindclone.di

import com.inik.blindclone.data.source.remote.api.ContentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesContentService(retrofit: Retrofit): ContentService
    = retrofit.create(ContentService::class.java)
}