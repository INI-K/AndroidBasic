package com.inik.blindclone.di

import com.google.gson.GsonBuilder
import com.inik.blindclone.util.DateUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory{
        return GsonConverterFactory.create(
            GsonBuilder()
                .setDateFormat(DateUtil.serverDateFormat.toPattern())
                .create()
        )
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient.Builder{
        return OkHttpClient.Builder().apply {
            connectTimeout(5, TimeUnit.SECONDS)
            readTimeout(5,TimeUnit.SECONDS)
            writeTimeout(5,TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient.Builder,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://devpub.co.kr:3030/api/v1/fastcampus/chapter8/list/")
            .client(client.build())
            .build()
    }
}