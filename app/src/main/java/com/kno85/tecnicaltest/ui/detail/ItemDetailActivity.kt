package com.kno85.tecnicaltest.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.kno85.tecnicaltest.R
import com.kno85.tecnicaltest.ui.main.MainActivity

class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))
        start(savedInstanceState)
    }

    private fun start(savedInstanceState:Bundle?) {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        ItemDetailFragment.ARG_ITEM_ID,
                        intent.getIntExtra(ItemDetailFragment.ARG_ITEM_ID,0))
                     putString(
                        ItemDetailFragment.ARG_ITEM_NAME,
                        intent.getStringExtra(ItemDetailFragment.ARG_ITEM_NAME))
                     putString(
                        ItemDetailFragment.ARG_ITEM_DESCRIPTION,
                        intent.getStringExtra(ItemDetailFragment.ARG_ITEM_DESCRIPTION))
                     putString(
                        ItemDetailFragment.ARG_ITEM_IMAGE_URL,
                        intent.getStringExtra(ItemDetailFragment.ARG_ITEM_IMAGE_URL))
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, MainActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}