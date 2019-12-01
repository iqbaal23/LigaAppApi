package com.example.ligaappapi.view.nextMatch

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view: NextMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson){

    fun getNextMatchList(idLeague: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatch(idLeague, "eventsnextleague.php")),
                MatchResponse::class.java)
            uiThread {
                view.hideLoading()
                if(data.match == null){
                    view.showEmptyMessage()
                } else{
                    view.showNextMatchList(data.match)
                }
            }
        }
    }
}