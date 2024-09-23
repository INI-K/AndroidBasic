package com.inik.todo.di

import androidx.lifecycle.ViewModel
import com.inik.todo.data.dao.ContentDao
import com.inik.todo.repository.ContentRepository
import com.inik.todo.repository.ContentRepositoryImpl
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
    fun providesContentRepository(contentDao: ContentDao): ContentRepository = ContentRepositoryImpl(contentDao)
}