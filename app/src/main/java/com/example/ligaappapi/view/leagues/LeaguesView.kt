package com.example.ligaappapi.view.leagues

import com.example.ligaappapi.model.League

interface LeaguesView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<League>)
}