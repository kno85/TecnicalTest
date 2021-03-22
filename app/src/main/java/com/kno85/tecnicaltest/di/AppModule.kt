package com.kno85.tecnicaltest.di

import android.app.Application
import androidx.room.Room
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.kno85.tecnicaltest.CharactersApp
import com.kno85.tecnicaltest.data.LocalDataSource
import com.kno85.tecnicaltest.data.RemoteDateSource
import com.kno85.tecnicaltest.network.RetrofitDataSource
import com.kno85.tecnicaltest.room.CharactersDatabase
import com.kno85.tecnicaltest.room.RoomDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {
    val mApp:Application = Application()

    @Provides
    @Singleton
    fun AppProvider(): Application {
        return mApp
    }

    @Provides
    fun databaseProvider(app: CharactersApp) = Room.databaseBuilder(
        app.applicationContext,
        CharactersDatabase::class.java,
        "CharactersBd"
    ).build()

    @Provides
    fun localDatasourceProvider(database: CharactersDatabase) : LocalDataSource = RoomDataSource(
        database
    )

    @Provides
    fun remoteDatasourceProvider() : RemoteDateSource = RetrofitDataSource()

}