package com.example.ridwan.submissionii.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.ridwan.submissionii.*
import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Model.Player
import com.example.ridwan.submissionii.Presenter.PlayerPresenter
import com.example.ridwan.submissionii.View.PlayerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_player.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class ListPlayerFragment : Fragment(), AnkoComponent<Context>, PlayerView {

    private lateinit var listPlayer: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var playerPresenter : PlayerPresenter
    private lateinit var teamId : String
    private lateinit var adapter : ListPlayerAdapter
    private var players: MutableList<Player> = mutableListOf()

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui){
            linearLayout {
                lparams(width = matchParent,height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                        R.color.colorAccent,android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    relativeLayout{
                        lparams(width = matchParent, height = wrapContent)
                        listPlayer = recyclerView {
                            id = R.id.list_player
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

        val request = ApiRepository()
        val gson = Gson()
        playerPresenter = PlayerPresenter(this,request,gson)

        if (arguments != null) {
            teamId = arguments?.getString("id")!!
        }

        playerPresenter.getPlayerList(teamId)
        adapter = ListPlayerAdapter(players){
            ctx.startActivity<DetailPlayerActivity>("name" to "${it.playerName}","position" to "${it.playerPosition}",
                                                    "height" to it.playerHeight, "description" to it.description ,
                                                    "weight" to it.playerWeight, "badge" to it.playerBadge)
        }
        listPlayer.adapter = adapter

        swipeRefresh.onRefresh {
            playerPresenter.getPlayerList(teamId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.Companion.create(requireContext()))
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerList(data: List<Player>) {
        swipeRefresh.isRefreshing = false
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }
}