package com.example.ligaappapi.view.searchTeams

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.TeamResponse
import com.example.ligaappapi.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter (private val view: SearchTeamView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun searchTeamList(teams: String?){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.searchTeam(teams)).await(),
                TeamResponse::class.java)

            view.hideLoading()
            if (data.teams == null){
                view.showEmptyMessage()
            } else{
                view.showSearchTeamList(data.teams)
            }
        }
    }
}