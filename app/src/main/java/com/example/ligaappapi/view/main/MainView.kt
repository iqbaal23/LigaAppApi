package com.example.ligaappapi.view.main

import com.example.ligaappapi.model.League

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<League>)
}