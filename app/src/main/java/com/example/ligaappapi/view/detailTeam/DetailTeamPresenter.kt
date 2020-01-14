package com.example.ligaappapi.view.detailTeam

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.TeamResponse
import com.example.ligaappapi.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTeamPresenter (private val view: DetailTeamView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getDetailTeam(idTeam: String?){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val team = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(idTeam)).await(),
                TeamResponse::class.java)

            view.hideLoading()
            view.showDetailTeamList(team.teams)
        }
    }
}