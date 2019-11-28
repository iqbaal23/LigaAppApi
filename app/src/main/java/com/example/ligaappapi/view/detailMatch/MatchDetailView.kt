package com.example.ligaappapi.view.detailMatch

import com.example.ligaappapi.model.Match
import com.example.ligaappapi.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetailList(match: List<Match>, homeTeam: List<Team>, awayTeam: List<Team>)
}