package com.kno85.tecnicaltest.ui.detail.mvp

import com.kno85.tecnicaltest.domain.Character


interface DetailView {

    fun showNoChangesResponse()
    fun showChangesResponse()
    fun showError()
}
