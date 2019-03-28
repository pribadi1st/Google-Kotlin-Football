package com.example.ridwan.submissionii.Presenter

import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Api.MatchDBApi
import com.example.ridwan.submissionii.Api.SearchDBApi
import com.example.ridwan.submissionii.Provider.CoroutineContextProvider
import com.example.ridwan.submissionii.Response.MatchResponse
import com.example.ridwan.submissionii.Response.SearchMatchResponse
import com.example.ridwan.submissionii.View.LastMatchView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.lang.Exception

class MatchPresenter(private val view: LastMatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchList(type : String?,id :String?){
        try {
            async(context.main) {
                val data = bg{ gson.fromJson(apiRepository
                        .doRequest(MatchDBApi.getMatch(type,id?.toInt())),
                        MatchResponse::class.java
                    )
                }
                view.showMatchList(data.await().events!!)
            }
        }catch (e:Exception){
            println(e.toString() + " ERROR GET MATCH List")
        }
    }

    fun searchMatch(query : String?){
        try {
            async(context.main) {
                val data = bg{ gson.fromJson(apiRepository
                    .doRequest(SearchDBApi.getMatch(query)),
                    SearchMatchResponse::class.java
                )
                }
                view.showMatchList(data.await().event!!)
            }
        }catch (e:Exception){
            println(e.toString() + " ERROR GET MATCH List")
        }
    }
}