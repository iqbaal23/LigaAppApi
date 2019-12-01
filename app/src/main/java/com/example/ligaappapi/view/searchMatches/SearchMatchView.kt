package com.example.ligaappapi.view.searchMatches

import com.example.ligaappapi.model.Match

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchMatchList(data: List<Match>)
    fun showEmptyMessage()
}