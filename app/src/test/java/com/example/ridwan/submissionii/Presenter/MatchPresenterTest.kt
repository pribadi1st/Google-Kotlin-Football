package com.example.ridwan.submissionii.Presenter

import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Api.MatchDBApi
import com.example.ridwan.submissionii.Model.Match
import com.example.ridwan.submissionii.Provider.TestContextProvider
import com.example.ridwan.submissionii.Response.MatchResponse
import com.example.ridwan.submissionii.View.LastMatchView
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private lateinit var view : LastMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchList() {
        val matchs: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matchs)
        val league = "eventspastleague"
        val id = 4328

        `when`(gson.fromJson(apiRepository
            .doRequest(MatchDBApi.getMatch(league,id)),
            MatchResponse::class.java
        )).thenReturn(response)

        presenter.getMatchList(league)
        verify(view).showMatchList(matchs)
    }
}