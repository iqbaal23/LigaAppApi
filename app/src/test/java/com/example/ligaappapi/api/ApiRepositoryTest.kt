package com.example.ligaappapi.api

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun testDoRequest() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_leagues.php?s=Soccer"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)

    }
}