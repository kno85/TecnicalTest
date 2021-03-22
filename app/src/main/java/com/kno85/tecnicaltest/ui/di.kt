package com.acano.conciertosmadrid.ui

import com.kno85.tecnicaltest.data.CharactersRepository
import com.kno85.tecnicaltest.ui.main.mvp.MainPresenterImp
import com.kno85.tecnicaltest.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainActivityModule {



    @Provides
    fun getMainPresenterProvider(UseCases: UseCases) = MainPresenterImp(UseCases)

    @Provides
    fun getReposProvider(charactersRepository: CharactersRepository) =
        UseCases(charactersRepository)
}

    @Subcomponent(modules = [(MainActivityModule::class)])
    interface MainActivityComponent {
        val mainPresenerImp: MainPresenterImp
    }
