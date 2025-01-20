package com.example.snapfood.core.di.module

import com.example.snapfood.data.repository.CharacterRepositoryImpl
import com.example.snapfood.data.repository.FilmRepositoryImpl
import com.example.snapfood.domain.repository.CharacterRepository
import com.example.snapfood.domain.repository.FilmRepository
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
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository


    @Binds
    @Singleton
    abstract fun bindFilmRepository(
        filmRepositoryImpl: FilmRepositoryImpl
    ): FilmRepository
}