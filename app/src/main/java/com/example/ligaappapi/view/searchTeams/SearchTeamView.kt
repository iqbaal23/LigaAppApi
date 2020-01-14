package com.example.ligaappapi.view.searchTeams

import com.example.ligaappapi.model.Team

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showSearchTeamList(data: List<Team>)
    fun showEmptyMessage()
}