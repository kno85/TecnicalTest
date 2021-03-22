package com.acano.conciertosmadrid.ui

import com.kno85.tecnicaltest.data.CharactersRepository
import com.kno85.tecnicaltest.ui.detail.mvp.DetailPresenterImp
import com.kno85.tecnicaltest.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailFragmentModule {



    @Provides
    fun getDetailPresenterProvider(UseCases: UseCases) = DetailPresenterImp(UseCases)

    @Provides
    fun getReposProvider(charactersRepository: CharactersRepository) =
        UseCases(charactersRepository)
}

    @Subcomponent(modules = [(DetailFragmentModule::class)])
    interface DetailFragmentComponent {
        val detailPresenerImp: DetailPresenterImp
    }
