package com.kno85.tecnicaltest.usecases

import com.kno85.tecnicaltest.data.CharactersRepository
import com.kno85.tecnincaltest.ui.main.mockedCharacter
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UseCaseTest {

    @Mock
    lateinit var charactersRepository: CharactersRepository

    @Mock
    lateinit var useCase: UseCases


    @Before
    fun setUp() {
        useCase = UseCases(charactersRepository)
    }

    @Test
    fun `invoke calls characters repository`() {
        runBlocking {

            val characters = listOf(mockedCharacter.copy(id = 1))
            whenever(charactersRepository.getCharacters()).thenReturn(characters)

            val result = charactersRepository.getCharacters()

            Assert.assertEquals(characters, result)
        }
    }
}