package com.example.ligaappapi.view.standings

import com.example.ligaappapi.model.Standing

interface StandingsView {
    fun showLoading()
    fun hideLoading()
    fun showStandingsList(data: List<Standing>)
    fun showEmptyMessage()
}