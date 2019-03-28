package com.example.ridwan.submissionii.Fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ridwan.submissionii.R

class FavoriteFragment : Fragment(){
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_favorite, container, false)
        viewPager = view.findViewById(R.id.view_pager_favorite)
        tabs = view.findViewById(R.id.tabLayout)
        val fragmentAdapter = ViewPagerFavoriteAdapter(childFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)
        return view
    }
}