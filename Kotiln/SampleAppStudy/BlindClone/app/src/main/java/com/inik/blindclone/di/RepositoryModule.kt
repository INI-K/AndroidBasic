package com.inik.blindclone.di

import com.inik.blindclone.data.repository.ContentRepositoryImpl
import com.inik.blindclone.data.source.remote.api.ContentService
import com.inik.blindclone.domain.repository.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesContentRepository(
        contentService: ContentService
    ): ContentRepository = ContentRepositoryImpl(contentService)
}