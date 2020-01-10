package com.example.ligaappapi.view.searchMatches

import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.api.TheSportDBApi
import com.example.ligaappapi.model.SearchMatchResponse
import com.example.ligaappapi.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter (private val view: SearchMatchView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun searchMatchList(event: String?){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.searchMatch(event)).await(),
                SearchMatchResponse::class.java)

            view.hideLoading()
            if (data.searchMatch == null){
                view.showEmptyMessage()
            } else{
                view.showSearchMatchList(data.searchMatch)
            }

        }
    }
}