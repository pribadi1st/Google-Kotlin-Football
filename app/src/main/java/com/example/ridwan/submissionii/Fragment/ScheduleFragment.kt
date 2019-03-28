package com.example.ridwan.submissionii.Fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.*
import com.example.ridwan.submissionii.R
import com.example.ridwan.submissionii.SearchActivity
import org.jetbrains.anko.startActivity

class ScheduleFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_schedule, container, false)
        viewPager = view.findViewById(R.id.viewpager_main)
        tabs = view.findViewById(R.id.tabLayout)
        setHasOptionsMenu(true)
        val fragmentAdapter = ViewPagerAdapter(childFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_bar, menu)
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView?
        searchView?.queryHint = "Find Club"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.startActivity<SearchActivity>("query" to  query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })

        searchView?.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {

                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}