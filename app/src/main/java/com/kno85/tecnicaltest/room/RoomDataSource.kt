package com.kno85.tecnicaltest.room

import com.kno85.tecnicaltest.data.LocalDataSource
import com.kno85.tecnicaltest.mappers.domainToRoom
import com.kno85.tecnicaltest.mappers.roomToDomain
import com.kno85.tecnicaltest.domain.Character as DomainCharacters

class RoomDataSource(db:CharactersDatabase) : LocalDataSource {

    private val charactersDao = db.charactersDao()

    override suspend fun saveCharacters(characters: List<DomainCharacters>) {
        charactersDao.insertItems(domainToRoom(characters))
    }

    override suspend fun getCharacters(): List<DomainCharacters> {
        return when {
            charactersDao.getItems().isNullOrEmpty() -> {
                emptyList()
            }
            else -> {
                roomToDomain(charactersDao.getItems())
            }
        }
    }
}