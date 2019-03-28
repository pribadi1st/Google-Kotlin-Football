package com.example.ridwan.submissionii

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ridwan.submissionii.Fragment.*
import com.example.ridwan.submissionii.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                trophy ->{
                    loadMatchFragment(savedInstanceState)
                }
                team ->{
                    loadTeamsFragment(savedInstanceState)
                }
                favorite -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = trophy
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, ScheduleFragment(), ScheduleFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, ListTeamFragment(), ListTeamFragment::class.java.simpleName)
                .commit()
        }
    }
}
