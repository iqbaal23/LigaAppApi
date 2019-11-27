package com.example.ligaappapi.view.LastMatch

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastMatchPresenter(private val view: LastMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson){

    fun getLastMatchList(idLeague: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatch(idLeague, "eventspastleague.php")),
                MatchResponse::class.java)
            uiThread {
                view.hideLoading()
                if(data.match != null){
                    view.showLastMatchList(data.match)
                }
            }
        }
    }
}