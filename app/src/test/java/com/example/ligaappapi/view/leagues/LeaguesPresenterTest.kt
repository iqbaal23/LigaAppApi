package com.example.ligaappapi.view.leagues

import com.example.ligaappapi.TestContextProvider
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.League
import com.example.ligaappapi.model.LeagueResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeaguesPresenterTest {
    @Mock
    private lateinit var view: LeaguesView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: LeaguesPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = LeaguesPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLeagueList() {
        val leagues: MutableList<League> = mutableListOf()
        val response = LeagueResponse(leagues)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLeagueList()

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLeagueList(leagues)
            Mockito.verify(view).hideLoading()
        }

    }
}