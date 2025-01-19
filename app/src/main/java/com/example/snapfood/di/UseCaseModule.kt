package com.example.snapfood.di

import com.example.snapfood.data.repository.CharacterRepository
import com.example.snapfood.domain.repository.ICharacterRepository
import com.example.snapfood.domain.usecase.GetCharacterDetailsUseCase
import com.example.snapfood.domain.usecase.SearchCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    
    @Provides
    fun provideGetCharacterDetailsUseCase(
        iCharacterRepository: ICharacterRepository
    ): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(iCharacterRepository)
    }

    @Provides
    fun provideSearchCharactersUseCase(
        iCharacterRepository: ICharacterRepository
    ): SearchCharactersUseCase {
        return SearchCharactersUseCase(iCharacterRepository)
    }
}