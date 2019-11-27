package com.example.ligaappapi.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    var match: List<Match>
)