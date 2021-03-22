package com.kno85.tecnicaltest.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kno85.tecnicaltest.R
import com.kno85.tecnicaltest.domain.Character
import com.kno85.tecnicaltest.ui.ItemDetailActivity
import com.kno85.tecnicaltest.ui.ItemDetailFragment
import com.kno85.tecnicaltest.utils.inflate

class SimpleItemRecyclerViewAdapter(private val parentActivity: MainActivity,
                                    private val values: List<Character>,
                                    private val twoPane: Boolean) :
    RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Character
            if (twoPane) {
                twoPaneConfig(item)
            } else {
                singlePaneConfig(item, v)
            }
        }
    }

    private fun singlePaneConfig(item: Character, v: View) {
        val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
            putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
            putExtra(ItemDetailFragment.ARG_ITEM_NAME, item.name)
            putExtra(ItemDetailFragment.ARG_ITEM_DESCRIPTION, item.description)
            putExtra(ItemDetailFragment.ARG_ITEM_IMAGE_URL, item.thumbnail)
        }
        v.context.startActivity(intent)

    }

    private fun twoPaneConfig(item: Character) {
        val fragment = ItemDetailFragment()
            .apply {
                arguments = Bundle().apply {
                    putInt(ItemDetailFragment.ARG_ITEM_ID, item.id!!)
                    putString(ItemDetailFragment.ARG_ITEM_NAME, item.name)
                    putString(ItemDetailFragment.ARG_ITEM_DESCRIPTION, item.description)
                    putString(ItemDetailFragment.ARG_ITEM_IMAGE_URL, item.thumbnail)
                }
            }
        parentActivity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.item_detail_container, fragment)
            .commit()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_list_content, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.textView.text = item.name

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.id_text)
    }
}