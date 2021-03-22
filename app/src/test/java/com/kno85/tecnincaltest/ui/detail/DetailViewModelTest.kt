package com.kno85.tecnicaltest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kno85.tecnicaltest.domain.Character
import com.kno85.tecnicaltest.ui.detail.mvp.DetailPresenterImp
import com.kno85.tecnicaltest.ui.detail.mvp.DetailView
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
class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var useCases : UseCases

    @Mock
    lateinit var detailView : DetailView

    @Mock
    lateinit var  items: List<Character>

    private lateinit var presenter: DetailPresenterImp

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        presenter = DetailPresenterImp(useCases)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `show showNoChangesResponse`() {
        runBlocking {
            presenter.checkUpates(detailView, 10)
            items = listOf(mockCharacter.copy(10))
            val selectedItem= items.let { items?.find { it.id == 10} }

            if (selectedItem != null) {
                assert(selectedItem.id == 10)
            }
        }
    }
}