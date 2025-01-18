package com.example.snapfood.core.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Main App [Module] that provides default and public classes everywhere
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    /**
     * provides [Application] context as default context.
     */
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context) = context
}