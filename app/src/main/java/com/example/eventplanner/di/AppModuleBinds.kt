package com.example.eventplanner.di

import com.example.eventplanner.config.IAppConfig
import com.example.eventplanner.config.LocalhostAppConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun appConfig(appConfig: LocalhostAppConfig): IAppConfig
}