package com.example.snapfood.di

import com.example.snapfood.data.repository.CharacterRepository
import com.example.snapfood.domain.repository.ICharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(
        impl: CharacterRepository
    ): ICharacterRepository
}