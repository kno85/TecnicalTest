package com.kno85.tecnicaltest.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [CharactersBd::class], version = 3)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
}