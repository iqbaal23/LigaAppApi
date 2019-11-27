package com.example.ligaappapi.view.LastMatch

import com.example.ligaappapi.model.Match

interface LastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatchList(data: List<Match>)
}