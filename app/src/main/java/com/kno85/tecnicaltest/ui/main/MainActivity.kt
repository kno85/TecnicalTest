package com.kno85.tecnicaltest.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.acano.conciertosmadrid.ui.MainActivityComponent
import com.acano.conciertosmadrid.ui.MainActivityModule
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.kno85.tecnicaltest.R
import com.kno85.tecnicaltest.domain.Character
import com.kno85.tecnicaltest.ui.main.mvp.MainPresenterImp
import com.kno85.tecnicaltest.ui.main.mvp.MainView
import com.kno85.tecnicaltest.utils.app
import com.kno85.tecnicaltest.utils.setVisible
import com.kno85.tecnicaltest.utils.toast
import kotlinx.android.synthetic.main.activity_item_list.*


class MainActivity : AppCompatActivity(), MainView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var component: MainActivityComponent

    private val presenter: MainPresenterImp by lazy {component.mainPresenerImp}

    private var twoPane: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        component = app.component.plus(MainActivityModule())
        setupUi()
        presenter.start(this)
    }

    private fun setupUi() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
        recyclerView = findViewById(R.id.item_list)

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            twoPane = true
        }
    }


    override fun addItems(list: List<Character>) {
        val simpleItemRecyclerViewAdapter =   SimpleItemRecyclerViewAdapter(
            this,
            list,
            twoPane
        )
        recyclerView.adapter =simpleItemRecyclerViewAdapter    }


    override fun showError() {
        this.toast(resources.getString(R.string.conection_error))
    }

    override fun showLoadding() {
        loadingLayout.setVisible(true)
    }

    override fun hideLoadding() {
        loadingLayout.setVisible(false)

    }
}