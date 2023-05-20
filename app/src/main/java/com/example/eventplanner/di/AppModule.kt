package com.example.eventplanner.di

import com.example.eventplanner.config.IAppConfig
import com.example.eventplanner.config.LocalhostAppConfig
import com.example.eventplanner.data.network.EventService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun retrofit(appConfig: IAppConfig): Retrofit = Retrofit.Builder()
        .baseUrl(appConfig.backandUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun eventService(retrofit: Retrofit): EventService =
        retrofit.create(EventService::class.java)
}