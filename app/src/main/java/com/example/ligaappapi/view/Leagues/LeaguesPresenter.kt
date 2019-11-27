package com.example.ligaappapi.view.Leagues

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.LeagueResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeaguesPresenter (private val view: LeaguesView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson){


    fun getLeagueList(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLeague()),
                LeagueResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showLeagueList(data.league)
            }
        }
    }
}