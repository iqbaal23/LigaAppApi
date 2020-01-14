package com.example.ligaappapi.view.detailTeam

import com.example.ligaappapi.model.Team

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun showDetailTeamList(team: List<Team>)
}