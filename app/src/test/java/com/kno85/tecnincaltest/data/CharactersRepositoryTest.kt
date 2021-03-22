package com.kno85.tecnicaltest.data.repository

import com.kno85.tecnicaltest.data.CharactersRepository
import com.kno85.tecnicaltest.data.LocalDataSource
import com.kno85.tecnicaltest.data.RemoteDateSource
import com.kno85.tecnicaltest.domain.Character
import com.kno85.tecnicaltest.ui.detail.mvp.DetailPresenterImp
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDateSource


    lateinit var charactersRepository: CharactersRepository

    @Before
    fun setUp() {
        charactersRepository =
            CharactersRepository(localDataSource, remoteDataSource)
    }

    @Test
     fun `getCharacter gets from local data source first`() {
        runBlocking {

            val localCharacters = listOf(mockedCharacter.copy(1))
            whenever(localDataSource.getCharacters().isNullOrEmpty()).thenReturn(false)
            whenever(localDataSource.getCharacters()).thenReturn(localCharacters)

            val result = charactersRepository.getCharacters()

            assertEquals(localCharacters, result)
        }
    }


    @Test
     fun `getCharacters from remote dont save null`() {
        runBlocking {
            val remoteCharacters = null

            whenever(remoteDataSource.getCharacters().isNullOrEmpty()).thenReturn(true)
            whenever(remoteDataSource.getCharacters()).thenReturn(remoteCharacters)
            remoteCharacters?.let { localDataSource.saveCharacters(it) }

            assertEquals((localDataSource).getCharacters(), remoteDataSource.getCharacters())
        }
    }

    private val mockedCharacter = Character(
        11,
        "Title",
        "Description",
        "sampleurl"
    )
}