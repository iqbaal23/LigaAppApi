package com.example.ligaappapi.view.searchMatches

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.SearchMatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchMatchPresenter (private val view: SearchMatchView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson){

    fun searchMatchList(event: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.searchMatch(event)),
                SearchMatchResponse::class.java)
            uiThread {
                view.hideLoading()
                if (data.searchMatch == null){
                    view.showEmptyMessage()
                } else{
                    view.showSearchMatchList(data.searchMatch)
                }
            }
        }
    }
}