package com.example.ridwan.submissionii.Presenter

import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Api.LeagueDBApi
import com.example.ridwan.submissionii.Provider.CoroutineContextProvider
import com.example.ridwan.submissionii.Response.LeagueResponse
import com.example.ridwan.submissionii.View.LeagueView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class LeaguePresenter(private val view: LeagueView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLeagueList(){
        async(context.main) {
            val data = bg{ gson.fromJson(apiRepository
                .doRequest(LeagueDBApi.getListLeague()),
                LeagueResponse::class.java
                )
            }
            view.showLeagueList(data.await().leagues)
        }
    }
}