package com.kno85.tecnicaltest.usecases

import com.kno85.tecnicaltest.data.CharactersRepository
import com.kno85.tecnicaltest.domain.Character

class UseCases(val charactersRepository: CharactersRepository) {
    suspend fun invokeList(): List<Character>? {
        return charactersRepository.getCharacters()
    }
    suspend fun checkItem(id:Int): Character? {
        return charactersRepository.getCharacter(id)
    }
}
