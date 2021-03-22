package com.kno85.tecnicaltest

import android.app.Application
import com.kno85.tecnicaltest.di.DaggerMyCharactersComponent

class CharactersApp: Application() {

    lateinit var component: DaggerMyCharactersComponent

    override fun onCreate() {
        component = DaggerMyCharactersComponent
            .factory()
            .create(this) as DaggerMyCharactersComponent
        super.onCreate()
    }
}