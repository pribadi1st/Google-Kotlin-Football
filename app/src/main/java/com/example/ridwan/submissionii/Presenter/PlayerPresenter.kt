package com.example.ridwan.submissionii.Presenter

import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Api.PlayerDBApi
import com.example.ridwan.submissionii.Provider.CoroutineContextProvider
import com.example.ridwan.submissionii.Response.ListPlayerResponse
import com.example.ridwan.submissionii.View.PlayerView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter (private val view: PlayerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
    ){

    fun getPlayerList(id: String? ){
        view.showLoading()
        async(context.main) {
            val data = bg{ gson.fromJson(apiRepository
                .doRequest(PlayerDBApi.getPlayer(id)),
                ListPlayerResponse::class.java
            )
            }
            view.hideLoading()
            view.showPlayerList(data.await().player)
        }
    }
}