package com.example.ridwan.submissionii.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.DetailMatchActivity
import com.example.ridwan.submissionii.Model.League
import com.example.ridwan.submissionii.Model.Match
import com.example.ridwan.submissionii.Presenter.LeaguePresenter
import com.example.ridwan.submissionii.Presenter.MatchPresenter
import com.example.ridwan.submissionii.Provider.TestContextProvider
import com.example.ridwan.submissionii.R
import com.example.ridwan.submissionii.R.id.spinner
import com.example.ridwan.submissionii.RecyclerViewAdapter
import com.example.ridwan.submissionii.View.LastMatchView
import com.example.ridwan.submissionii.View.LeagueView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_list_match.*
import kotlinx.android.synthetic.main.fragment_list_match.view.*
import org.jetbrains.anko.support.v4.intentFor
import java.lang.Exception

class LastMatchFragment : Fragment(), LeagueView, LastMatchView {

    private var leagues: ArrayList<League> = ArrayList<League>()
    private var filterLeague: ArrayList<String> = ArrayList<String>()
    private var filterLeagueId: ArrayList<String> = ArrayList<String>()
    private lateinit var leaguePresenter: LeaguePresenter
    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var request : ApiRepository
    private lateinit var leagueId : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_list_match,container,false)
        val activity = activity
        adapter = RecyclerViewAdapter(activity!!, matches) {
            startActivity(
                intentFor<DetailMatchActivity>(
                    "eventId" to it.eventId,
                    "homeId" to it.homeId, "awayId" to it.awayId
                )
            )
        }
        view.list_matchs.layoutManager = LinearLayoutManager(activity)
        view.list_matchs.adapter = adapter

        request = ApiRepository()
        val gson = Gson()
        leaguePresenter = LeaguePresenter(this,request,gson)
        presenter = MatchPresenter(this, request, gson)

        leaguePresenter.getLeagueList()

        return view
    }

    override fun showLeagueList(data: ArrayList<League>) {
        leagues.clear()
        leagues.addAll(data)

        for (item in leagues)
            if (item.strSport == "Soccer") {
                filterLeague.add(item.strLeague!!)
                filterLeagueId.add(item.idLeague!!)
            }
        println(filterLeagueId.size.toString() + " SIZE NYA")

        val spinnerItems = filterLeague
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        league_spinner.adapter = spinnerAdapter

        league_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueId = league_spinner.selectedItem.toString()
                presenter.getMatchList(resources.getString(R.string.last_match),filterLeagueId[position])
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        adapter.notifyDataSetChanged()
    }

    override fun showMatchList(data: List<Match>) {
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }
}