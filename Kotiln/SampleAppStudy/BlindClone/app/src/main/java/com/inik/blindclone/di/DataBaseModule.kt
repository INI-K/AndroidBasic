package com.inik.blindclone.di

import android.content.Context
import androidx.room.Room
import com.inik.blindclone.data.source.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext context: Context): AppDataBase{
        return Room.databaseBuilder(context,AppDataBase::class.java,"BlindCloneDB")
            .fallbackToDestructiveMigration()
            .build()
    }
}