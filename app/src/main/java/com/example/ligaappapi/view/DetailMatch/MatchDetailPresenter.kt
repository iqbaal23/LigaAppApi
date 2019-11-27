package com.example.ligaappapi.view.DetailMatch

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?){
        doAsync {
            val homeTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(idHomeTeam)),
                TeamResponse::class.java)

            val awayTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(idAwayTeam)),
                TeamResponse::class.java)

            uiThread {
                view.showTeamDetailList(homeTeam.teams, awayTeam.teams)
            }
        }
    }
}