package com.kno85.tecnicaltest.di

import com.kno85.tecnicaltest.data.CharactersRepository
import com.kno85.tecnicaltest.usecases.UseCases
import dagger.Module
import dagger.Provides
@Module
class UseCasesModule {
    @Provides
    fun getCharacters(charactersRepository: CharactersRepository) =
        UseCases(charactersRepository)
}