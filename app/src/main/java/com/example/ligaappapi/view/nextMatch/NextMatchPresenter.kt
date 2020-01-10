package com.example.ligaappapi.view.nextMatch

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.MatchResponse
import com.example.ligaappapi.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextMatchPresenter(private val view: NextMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getNextMatchList(idLeague: String?){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatch(idLeague, "eventsnextleague.php")).await(),
                MatchResponse::class.java)

            view.hideLoading()
            if(data.match == null){
                view.showEmptyMessage()
            } else {
                view.showNextMatchList(data.match)
            }
        }
    }
}