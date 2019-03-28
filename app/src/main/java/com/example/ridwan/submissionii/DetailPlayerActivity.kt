package com.example.ridwan.submissionii

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {

    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        val extra = intent.extras
        position_player.text = extra.getString("position")
        Picasso.get().load(extra.getString("badge")).resize(300,300).into(badge_player)
        height_player.text = extra.getString("height")
        weight_player.text = extra.getString("weight")
        description_player.text = extra.getString("description")
        supportActionBar?.title = extra.getString("name")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
