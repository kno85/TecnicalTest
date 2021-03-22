package com.kno85.tecnicaltest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.acano.conciertosmadrid.ui.DetailFragmentModule
import com.acano.conciertosmadrid.ui.DetailFragmentComponent
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kno85.tecnicaltest.R
import com.kno85.tecnicaltest.di.GlideApp
import com.kno85.tecnicaltest.ui.detail.mvp.DetailPresenterImp
import com.kno85.tecnicaltest.ui.detail.mvp.DetailView
import com.kno85.tecnicaltest.utils.app
import com.kno85.tecnicaltest.utils.loadUrl
import com.kno85.tecnicaltest.utils.toast
import kotlinx.android.synthetic.main.item_detail.*

class ItemDetailFragment : Fragment(),DetailView {

    private lateinit var component: DetailFragmentComponent

    private val presenter: DetailPresenterImp by lazy {component.detailPresenerImp}

    private var id: Int? = null
    private var name: String? = null
    private var desc: String? = null
    private var img: String? = null

    companion object {
        const val ARG_ITEM_ID = "item_id"
        const val ARG_ITEM_NAME = "item_name"
        const val ARG_ITEM_DESCRIPTION = "item_description"
        const val ARG_ITEM_IMAGE_URL = "item_image_url"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = context?.app?.component?.plusDetail(DetailFragmentModule())!!

        arguments?.let {
            if (it.containsKey(ARG_ITEM_NAME)) {
                id = it.getInt(ARG_ITEM_ID)
                name = it.getString(ARG_ITEM_NAME)
                desc = it.getString(ARG_ITEM_DESCRIPTION)
                img = it.getString(ARG_ITEM_IMAGE_URL)
                activity?.
                findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.
                title = name
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)
        rootView.findViewById<TextView>(R.id.item_text_detail).text = desc
        val button = rootView.findViewById<Button>(R.id.item_checkUpdates)
        val iv= rootView.findViewById<ImageView>(R.id.item_image_detail)
        iv?.loadUrl(img)
        button.setOnClickListener{ id?.let { it1 -> presenter.checkUpates(this, it1) } }
        return rootView
    }

    override fun showNoChangesResponse() {
        context?.getString(R.string.items_no_changes)?.let { context?.toast(it) }
    }
    override fun showChangesResponse() {
        context?.getString(R.string.items_has_changed)?.let { context?.toast(it) }
    }
    override fun showError() {
        context?.getString(R.string.conection_error)?.let { context?.toast(it) }
    }
}