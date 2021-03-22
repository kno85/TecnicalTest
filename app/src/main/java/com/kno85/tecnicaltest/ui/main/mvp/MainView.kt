package com.kno85.tecnicaltest.ui.main.mvp

import com.kno85.tecnicaltest.domain.Character


interface MainView {

    fun addItems(list: List<Character>)
    fun showError()
    fun showLoadding()
    fun hideLoadding()
}
