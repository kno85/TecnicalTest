package com.kno85.tecnicaltest.di

import com.acano.conciertosmadrid.ui.DetailFragmentModule
import com.acano.conciertosmadrid.ui.DetailFragmentComponent
import com.acano.conciertosmadrid.ui.MainActivityComponent
import com.acano.conciertosmadrid.ui.MainActivityModule
import com.kno85.tecnicaltest.CharactersApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class] )
interface MyCharactersComponent {
    fun plus(module: MainActivityModule): MainActivityComponent
    fun plusDetail(module: DetailFragmentModule): DetailFragmentComponent

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: CharactersApp): MyCharactersComponent
    }
}