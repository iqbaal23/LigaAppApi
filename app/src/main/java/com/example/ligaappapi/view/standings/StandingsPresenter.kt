package com.example.ligaappapi.view.standings

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.StandingResponse
import com.example.ligaappapi.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingsPresenter(private val view: StandingsView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getStandingList(idLeague: String?){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getStanding(idLeague)).await(),
                StandingResponse::class.java)

            view.hideLoading()
            if (data == null){
                view.showEmptyMessage()
            } else{
                view.showStandingsList(data.standing)
            }
        }
    }
}