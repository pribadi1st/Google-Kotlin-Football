package com.example.ridwan.submissionii.Fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerFavoriteAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavoriteMatchFragment()
            }
            else -> {
                return FavoriteTeamFragment()
            }
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Matches"
            else -> {
                return "Teams"
            }
        }
    }

}