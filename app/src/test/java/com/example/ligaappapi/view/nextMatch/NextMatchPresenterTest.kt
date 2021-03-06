package com.example.ligaappapi.view.nextMatch

import com.example.ligaappapi.TestContextProvider
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.Match
import com.example.ligaappapi.model.MatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {
    @Mock
    private lateinit var view: NextMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetNextMatchList() {
        val matchs: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matchs)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatchList(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showNextMatchList(matchs)
            Mockito.verify(view).hideLoading()
        }
    }
}