package com.example.ligaappapi.view.teams

import com.example.ligaappapi.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<Team>)
    fun showEmptyMessage()
}