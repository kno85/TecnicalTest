package com.kno85.tecnicaltest.room

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface CharactersDao {

    @Query ("SELECT * FROM CharactersBd")
    fun getItems():List<CharactersBd>

    @Query ("SELECT COUNT(id) FROM CharactersBd")
    fun getItemCount():Int

    @Insert (onConflict = IGNORE)
    fun insertItems(list:List<CharactersBd>)

    @Update ()
    fun updateItem(item:CharactersBd)
}