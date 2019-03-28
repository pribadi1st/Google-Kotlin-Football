package com.example.ridwan.submissionii.Fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle



class ViewPagerTeamAdapter(fm : FragmentManager,private val id : String) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                val descriptionFragment = DescriptionFragment()
                return descriptionFragment
            }
            else -> {
                val bundle = Bundle()
                bundle.putString("id", id);
                val playerFragment= ListPlayerFragment()
                playerFragment.arguments = bundle
                return playerFragment

            }
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Description"
            else -> {
                return "Player"
            }
        }
    }

}