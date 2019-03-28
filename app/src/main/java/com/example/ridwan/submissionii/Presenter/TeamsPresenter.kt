package com.example.ridwan.submissionii.Presenter

import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Api.LeagueDBApi
import com.example.ridwan.submissionii.Api.MatchDBApi
import com.example.ridwan.submissionii.Api.SearchDBApi
import com.example.ridwan.submissionii.Provider.CoroutineContextProvider
import com.example.ridwan.submissionii.Response.TeamsResponse
import com.example.ridwan.submissionii.View.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter (
    private val view: TeamsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
    ){

    fun getTeamList(id: String? ){
        view.showLoading()
        async(context.main) {
            val data = bg{ gson.fromJson(apiRepository
                .doRequest(LeagueDBApi.getListTeam(id)),
                TeamsResponse::class.java
                )
            }
            view.hideLoading()
            view.showTeamList(data.await().teams)
        }
    }

    fun searchTeam(query: String? ){
//        view.showLoading()
        try {
            async(context.main) {
                val data = bg {
                    gson.fromJson(
                        apiRepository
                            .doRequest(SearchDBApi.getTeam(query)),
                        TeamsResponse::class.java
                    )
                }
                view.hideLoading()
                view.showTeamList(data.await().teams)
            }
        }catch (e : Exception){
            println("Error")
        }
    }
}