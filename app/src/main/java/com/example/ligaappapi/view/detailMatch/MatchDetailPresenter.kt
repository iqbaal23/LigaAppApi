package com.example.ligaappapi.view.detailMatch

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.MatchResponse
import com.example.ligaappapi.model.TeamResponse
import com.example.ligaappapi.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMatchDetail(idEvent:String?, idHomeTeam: String?, idAwayTeam: String?){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val match = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(idEvent)).await(),
                MatchResponse::class.java)

            val homeTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(idHomeTeam)).await(),
                TeamResponse::class.java)

            val awayTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(idAwayTeam)).await(),
                TeamResponse::class.java)

            view.hideLoading()
            view.showTeamDetailList(match.match, homeTeam.teams, awayTeam.teams)
        }
    }
}