package com.example.ligaappapi.view.detailMatch

import com.example.ligaappapi.TestContextProvider
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.Match
import com.example.ligaappapi.model.MatchResponse
import com.example.ligaappapi.model.Team
import com.example.ligaappapi.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {
    @Mock
    private lateinit var view: MatchDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetMatchDetail() {
        val match: MutableList<Match> = mutableListOf()
        val homeTeam: MutableList<Team> = mutableListOf()
        val awayTeam: MutableList<Team> = mutableListOf()
        val matchResponse = MatchResponse(match)
        val homeResponse = TeamResponse(homeTeam)
        val awayResponse = TeamResponse(awayTeam)
        val idEvent = "441613"
        val idHome = "133602"
        val idAway = "133602"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchResponse::class.java
                )
            ).thenReturn(matchResponse)

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(homeResponse)

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(awayResponse)

            presenter.getMatchDetail(idEvent, idHome, idAway)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamDetailList(match, homeTeam, awayTeam)
            Mockito.verify(view).hideLoading()
        }
    }
}