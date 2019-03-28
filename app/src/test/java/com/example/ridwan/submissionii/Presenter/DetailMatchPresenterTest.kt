package com.example.ridwan.submissionii.Presenter

import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Api.DetailMatchDBApi
import com.example.ridwan.submissionii.Api.DetailTeamDBApi
import com.example.ridwan.submissionii.Api.MatchDBApi
import com.example.ridwan.submissionii.Model.DetailMatch
import com.example.ridwan.submissionii.Model.DetailTeam
import com.example.ridwan.submissionii.Provider.TestContextProvider
import com.example.ridwan.submissionii.Response.DetailMatchResponse
import com.example.ridwan.submissionii.Response.DetailTeamResponse
import com.example.ridwan.submissionii.View.DetailMatchView
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {

    @Mock
    private lateinit var view : DetailMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun getMatchDetail() {
        val detailMatch: ArrayList<DetailMatch> = ArrayList()
        val homeTeams: ArrayList<DetailTeam> = ArrayList()
        val awayTeams: ArrayList<DetailTeam> = ArrayList()
        val response = DetailMatchResponse(detailMatch)
        val response2 = DetailTeamResponse(homeTeams)
        val response3 = DetailTeamResponse(awayTeams)
        val eventId = "576588"
        val homeTeam = "133610"
        val awayTeam = "133615"

        `when`(gson.fromJson(apiRepository
            .doRequest(DetailMatchDBApi.getMatch(eventId)),
            DetailMatchResponse::class.java
        )).thenReturn(response)

        `when`(gson.fromJson(apiRepository
            .doRequest(DetailTeamDBApi.getMatch(homeTeam)),
            DetailTeamResponse::class.java
        )).thenReturn(response2)

        `when`(gson.fromJson(apiRepository
            .doRequest(DetailTeamDBApi.getMatch(awayTeam)),
            DetailTeamResponse::class.java
        )).thenReturn(response3)

        presenter.getMatchDetail(eventId,homeTeam,awayTeam)
        verify(view).showMatchList(detailMatch,homeTeams,awayTeams)
    }
}