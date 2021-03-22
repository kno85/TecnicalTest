package com.kno85.tecnicaltest.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharactersBd")
data class CharactersBd(
    @PrimaryKey (autoGenerate = true)
    var id:Int?=0,
    var name:String?="",
    var description:String?="",
    var thumbnail: String?=""
)




