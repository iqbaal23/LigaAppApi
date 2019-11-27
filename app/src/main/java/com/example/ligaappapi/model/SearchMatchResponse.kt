package com.example.ligaappapi.model

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(
    @SerializedName("event")
    var searchMatch: List<Match>
)