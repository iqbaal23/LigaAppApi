package com.example.ligaappapi.view.DetailMatch

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.MatchResponse
import com.example.ligaappapi.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun getMatchDetail(idEvent:String?, idHomeTeam: String?, idAwayTeam: String?){
        view.showLoading()
        doAsync {
            val match = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(idEvent)),
                MatchResponse::class.java)

            val homeTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(idHomeTeam)),
                TeamResponse::class.java)

            val awayTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(idAwayTeam)),
                TeamResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showTeamDetailList(match.match, homeTeam.teams, awayTeam.teams)
            }
        }
    }
}