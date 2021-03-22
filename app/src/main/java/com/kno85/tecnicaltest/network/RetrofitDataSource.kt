package com.kno85.tecnicaltest.network

import com.kno85.tecnicaltest.BuildConfig
import com.kno85.tecnicaltest.data.RemoteDateSource
import com.kno85.tecnicaltest.domain.Character
import com.kno85.tecnicaltest.mappers.toDomainCharacter
import com.kno85.tecnicaltest.mappers.toDomainCharacterResult
import com.kno85.tecnicaltest.network.CharactersBuilder.service
import com.kno85.tecnicaltest.utils.getHash
import kotlin.random.Random

class RetrofitDataSource : RemoteDateSource {


    private val LIMIT: Int=100
    val ts:String= Random.nextInt().toString()
    val apiKey = BuildConfig.API_CLIENT_ID
    val hash= getHash(ts)
    override suspend fun getCharacters(): List<Character>?  {

        val query=service.getCharacters(ts,apiKey, hash, LIMIT).execute()
        return toDomainCharacter(query.body()?.results?.items)
    }
    override suspend fun getCharacter(id:Int): Character?  {
        val query=service.getCharacter(id,ts,apiKey, hash).execute()
        return toDomainCharacterResult(query.body()?.results?.items?.get(0))
    }
}


