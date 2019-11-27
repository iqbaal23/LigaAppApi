package com.example.ligaappapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    @SerializedName("idLeague")
    var leagueId: String? = null,

    @SerializedName("strLeague")
    var leagueName: String? = null,

    @SerializedName("strBadge")
    var leagueBadge: String? = null,

    @SerializedName("strCountry")
    var leagueCountry: String? = null,

    @SerializedName("strDescriptionEN")
    var leagueDesc: String? = null
): Parcelable