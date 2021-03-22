package com.kno85.tecnicaltest.di

import com.kno85.tecnicaltest.data.LocalDataSource
import com.kno85.tecnicaltest.data.RemoteDateSource
import com.kno85.tecnicaltest.data.CharactersRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun getCharacters(localReposRepository: LocalDataSource, remoteReposRepository: RemoteDateSource)=
        CharactersRepository(
            localReposRepository,
            remoteReposRepository
        )
}