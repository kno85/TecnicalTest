package com.kno85.tecnicaltest.mappers

import com.kno85.tecnicaltest.domain.Character
import com.kno85.tecnicaltest.network.model.Chararcter
import com.kno85.tecnicaltest.room.CharactersBd


fun toDomainCharacter(result: List<Chararcter>?): List<Character> {
    if (result != null) {
        return(result.map {
            Character(
                id= it.id,
                name = it.name,
                description = it.description,
                thumbnail = it.thumbnail?.path?.plus(".").plus(it.thumbnail?.extension))
        })
    }
    else
        return emptyList()
}
fun toDomainCharacterResult(result: Chararcter?): Character?{
    when {
        result != null -> {
            return Character(
                id= result.id,
                name = result.name,
                description = result.description,
                thumbnail = result.thumbnail?.path.plus(".").plus(result.thumbnail?.extension))
        }
        else -> return Character()
    }
}
fun domainToRoom(repos:List<Character>):List<CharactersBd>{
    return (repos.map { CharactersBd(
        id = it.id,
        name = it.name,
        description = it.description,
        thumbnail = it.thumbnail)
    })
}
fun roomToDomain(reposbd:List<CharactersBd>):List<Character> {
    return (reposbd.map {
        Character(
            id = it.id,
            name = it.name,
            description = it.description,
            thumbnail = it.thumbnail)
    })
}

