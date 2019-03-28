package com.example.ridwan.submissionii.Presenter

import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Api.DetailTeamDBApi
import com.example.ridwan.submissionii.Api.LeagueDBApi
import com.example.ridwan.submissionii.Provider.CoroutineContextProvider
import com.example.ridwan.submissionii.Response.DetailTeamResponse
import com.example.ridwan.submissionii.Response.TeamsResponse
import com.example.ridwan.submissionii.View.DetailTeamView
import com.example.ridwan.submissionii.View.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailTeamPresenter (
    private val view: DetailTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getTeamDetail(id: String? ){
        async(context.main) {
            val data = bg{ gson.fromJson(apiRepository
                .doRequest(DetailTeamDBApi.getTeam(id)),
                DetailTeamResponse::class.java
                )
            }
            view.showDetailTeam(data.await().teams)
        }
    }
}