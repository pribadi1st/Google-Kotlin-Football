package com.example.ridwan.submissionii.Fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LastMatchFragment()
            }
            else -> {
                return NextMatchFragment()
            }
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Last Match"
            else -> {
                return "Next Match"
            }
        }
    }

}