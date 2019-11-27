package com.example.ligaappapi.view.DetailMatch

import com.example.ligaappapi.model.Team

interface MatchDetailView {
    fun showTeamDetailList(homeTeam: List<Team>, awayTeam: List<Team>)
}