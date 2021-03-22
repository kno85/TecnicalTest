package com.kno85.tecnicaltest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kno85.tecnicaltest.domain.Character
import com.kno85.tecnicaltest.ui.main.mvp.MainPresenterImp
import com.kno85.tecnicaltest.ui.main.mvp.MainView
import com.kno85.tecnicaltest.usecases.UseCases
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var useCases : UseCases

    @Mock
    lateinit var mainView : MainView

    @Mock

    var items: List<Character>? = listOf(mockCharacter.copy(1))


    private lateinit var presenter: MainPresenterImp

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        presenter = MainPresenterImp(useCases)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `show loadding at start`() {
        runBlocking {
            presenter.start(mainView)

            verify(mainView).showLoadding()
        }
    }
    @Test
    fun `hide loadding when finish`() {
        runBlocking {
            presenter.start(mainView)

            verify(mainView).hideLoadding()
        }
    }
    @Test
    fun `show error after api call`() {
        runBlocking {
            presenter.start(mainView)
            verify(mainView).showError()
        }
    }
    @Test
    fun `get info after api call`() {
        runBlocking {
            presenter.start(mainView)
            let { items= useCases.invokeList() }
            items?.let { verify(mainView).addItems(it) }
        }
    }
}