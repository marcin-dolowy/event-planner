package com.example.eventplanner.di

import android.app.Application
import android.location.Geocoder
import androidx.room.Room
import com.example.eventplanner.config.IAppConfig
import com.example.eventplanner.data.local.EventDao
import com.example.eventplanner.data.local.EventDatabase
import com.example.eventplanner.data.network.EventService
import com.example.eventplanner.ui.utils.ToastViewer
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

    @Singleton
    @Provides
    fun toastViewer(application: Application) = ToastViewer(application.applicationContext)

    @Singleton
    @Provides
    fun geocoder(application: Application) = Geocoder(application.applicationContext)

    @Singleton
    @Provides
    fun eventDatabase(application: Application): EventDatabase = Room
        .databaseBuilder(
            application.applicationContext,
            EventDatabase::class.java,
            "EventDatabase"
        )
        .build()

    @Singleton
    @Provides
    fun eventDao(eventDatabase: EventDatabase): EventDao = eventDatabase.eventDao()
}