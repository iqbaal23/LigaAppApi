package com.example.ligaappapi.view.lastMatch

import com.example.ligaappapi.model.Match

interface LastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatchList(data: List<Match>)
    fun showEmptyMessage()
}