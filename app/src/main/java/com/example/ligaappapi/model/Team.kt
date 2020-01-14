package com.example.ligaappapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
    @SerializedName("idTeam")
    var teamId: String? = null,
    @SerializedName("strTeam")
    var teamName: String? = null,
    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,
    @SerializedName("strLeague")
    var teamLeague: String? = null,
    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null,
    @SerializedName("strSport")
    var strSport: String? = null
): Parcelable