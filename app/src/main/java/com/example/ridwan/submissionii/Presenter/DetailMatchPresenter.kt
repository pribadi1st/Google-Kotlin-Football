package com.example.ridwan.submissionii.Presenter

import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Api.DetailMatchDBApi
import com.example.ridwan.submissionii.Api.DetailTeamDBApi
import com.example.ridwan.submissionii.Provider.CoroutineContextProvider
import com.example.ridwan.submissionii.Response.DetailMatchResponse
import com.example.ridwan.submissionii.Response.DetailTeamResponse
import com.example.ridwan.submissionii.View.DetailMatchView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(private val view: DetailMatchView, private val apiRepository : ApiRepository,
                           private val gson : Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
){
    fun getMatchDetail(id: String,team : String?, away: String?){
        async (context.main) {
            val data = bg{ gson.fromJson(apiRepository.doRequest(DetailMatchDBApi.getMatch(id)),
                DetailMatchResponse::class.java
                )
            }

            val data2 = bg{ gson.fromJson(apiRepository.doRequest(DetailTeamDBApi.getTeam(team)),
                    DetailTeamResponse::class.java
                )
            }

            val data3 = bg {
                gson.fromJson(
                    apiRepository.doRequest(DetailTeamDBApi.getTeam(away)),
                    DetailTeamResponse::class.java
                )
            }

//            uiThread {
            view.showMatchList(data.await().events!! ,data2.await().teams,data3.await().teams)
//            }
        }
    }
}