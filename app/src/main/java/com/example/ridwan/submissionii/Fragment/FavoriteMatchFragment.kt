package com.example.ridwan.submissionii.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ridwan.submissionii.DB.Favorite
import com.example.ridwan.submissionii.DB.database
import com.example.ridwan.submissionii.DetailMatchActivity
import com.example.ridwan.submissionii.FavoriteAdapter
import com.example.ridwan.submissionii.R
import kotlinx.android.synthetic.main.fragment_list_match.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.intentFor

class FavoriteMatchFragment : Fragment() {

    private var matches: ArrayList<Favorite> = ArrayList()
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_list_match,container,false)

        val activity = activity
        adapter = FavoriteAdapter(activity!!, matches ) {
            startActivity(
                intentFor<DetailMatchActivity>(
                    "eventId" to it.eventId,
                    "homeId" to it.homeId, "awayId" to it.awayId
                )
            )
        }

        view.list_matchs.layoutManager = LinearLayoutManager(activity)
        view.list_matchs.adapter = adapter

        showFavorite()

        return view
    }

    private fun showFavorite(){
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)

            val favorite = result.parseList(classParser<Favorite>())
            matches.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}