package com.example.ligaappapi.view.NextMatch

import com.example.ligaappapi.model.Match

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data: List<Match>)
}