package com.example.ligaappapi.model

import com.google.gson.annotations.SerializedName

data class StandingResponse(
    @SerializedName("table")
    var standing: List<Standing>
)