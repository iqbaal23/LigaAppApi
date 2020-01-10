package com.example.ligaappapi.view.searchMatches

import com.example.ligaappapi.TestContextProvider
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.Match
import com.example.ligaappapi.model.SearchMatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchMatchPresenterTest {
    @Mock
    private lateinit var view: SearchMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testSearchMatchList() {
        val matchs: MutableList<Match> = mutableListOf()
        val response = SearchMatchResponse(matchs)
        val event = "Arsenal"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    SearchMatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.searchMatchList(event)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showSearchMatchList(matchs)
            Mockito.verify(view).hideLoading()
        }
    }
}