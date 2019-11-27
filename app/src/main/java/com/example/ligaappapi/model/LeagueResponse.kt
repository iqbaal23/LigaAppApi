package com.example.ligaappapi.model

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("countrys")
    val league: List<League>
)