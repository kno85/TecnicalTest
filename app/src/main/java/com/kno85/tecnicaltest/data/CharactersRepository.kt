package com.kno85.tecnicaltest.data

import com.kno85.tecnicaltest.domain.Character

class CharactersRepository (private val localReposRepository: LocalDataSource,
                            private val remoteReposRepository: RemoteDateSource) {

    suspend fun getCharacters(): List<Character> {
        var characterList: List<Character>?= emptyList<Character>()
        if (localReposRepository.getCharacters().isNullOrEmpty()) {
            characterList = remoteReposRepository.getCharacters()
            localReposRepository.saveCharacters(characterList!!)
        }
        return localReposRepository.getCharacters()
    }
    suspend fun getCharacter(id:Int): Character? {
        return remoteReposRepository.getCharacter(id)
    }
}

interface LocalDataSource {
    suspend fun saveCharacters(characters: List<Character>)
    suspend fun getCharacters(): List<Character>
}

interface RemoteDateSource {
    suspend fun getCharacters(): List<Character>?
    suspend fun getCharacter(id:Int): Character?
}
