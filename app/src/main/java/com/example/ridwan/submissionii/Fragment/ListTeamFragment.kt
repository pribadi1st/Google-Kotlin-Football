package com.example.ridwan.submissionii.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.ridwan.submissionii.*
import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Model.League
import com.example.ridwan.submissionii.Model.Teams
import com.example.ridwan.submissionii.Presenter.LeaguePresenter
import com.example.ridwan.submissionii.Presenter.TeamsPresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import com.example.ridwan.submissionii.R.color.colorAccent
import com.example.ridwan.submissionii.View.LeagueView
import com.example.ridwan.submissionii.View.TeamsView
import com.google.gson.Gson
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.widget.*


class ListTeamFragment : Fragment(), AnkoComponent<Context>, LeagueView, TeamsView {

    private var leagues: ArrayList<League> = ArrayList<League>()
    private var filterLeague: ArrayList<String> = ArrayList<String>()
    private lateinit var leaguePresenter: LeaguePresenter
    private var teams: MutableList<Teams> = mutableListOf()
    private lateinit var teamPresenter: TeamsPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var leagueName: String
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui){
            linearLayout {
                lparams(width = matchParent,height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                spinner = spinner{
                    id = R.id.spinner
                }
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(colorAccent,android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    relativeLayout{
                        lparams(width = matchParent, height = wrapContent)
                        listTeam = recyclerView {
                            id = R.id.list_team
                            lparams (width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(context)
                        }

                        progressBar = progressBar {
                        }.lparams{
                            centerHorizontally()
                        }
                    }
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        val request = ApiRepository()
        val gson = Gson()
        leaguePresenter = LeaguePresenter(this,request,gson)
        teamPresenter = TeamsPresenter(this,request,gson)

        adapter = TeamAdapter(teams){
            ctx.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }
        listTeam.adapter = adapter
        leaguePresenter.getLeagueList()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                println(leagueName + " leagueName")
                teamPresenter.getTeamList(leagueName)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            teamPresenter.getTeamList(leagueName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.Companion.create(requireContext()))
    }

    override fun showLeagueList(data: ArrayList<League>) {
        leagues.clear()
        leagues.addAll(data)

        for (item in leagues)
            if(item.strSport == "Soccer")
                filterLeague.add(item.strLeague!!)

        val spinnerItems = filterLeague
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_bar, menu)
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView?
        searchView?.queryHint = "Find Club"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if(query!!.isNotEmpty()){
                    spinner.invisible()
                    teamPresenter.searchTeam(query)
                }else{
                    spinner.visible()
                    teamPresenter.getTeamList(leagueName)
                }
                return false
            }
        })

        searchView?.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                teamPresenter.getTeamList(leagueName)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun showTeamList(data: List<Teams>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}