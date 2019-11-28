package com.example.ligaappapi.view.nextMatch

import com.example.ligaappapi.model.Match

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data: List<Match>)
}