package com.example.ligaappapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    @SerializedName("idEvent") var idMatch: String? = null,
    @SerializedName("strEvent") var strMatch: String? = null,
    @SerializedName("strSport") var strSport: String? = null,
    @SerializedName("idHomeTeam") var idHomeTeam: String? = null,
    @SerializedName("idAwayTeam") var idAwayTeam: String? = null,
    @SerializedName("strHomeTeam") var homeTeam: String? = null,
    @SerializedName("strAwayTeam") var awayTeam: String? = null,
    @SerializedName("intHomeScore") var homeScore: String? = null,
    @SerializedName("intAwayScore") var awayScore: String? = null,
    @SerializedName("dateEvent") var dateMatch: String? = null,
    @SerializedName("strHomeGoalDetails") var homeGoalDetail: String? = null,
    @SerializedName("strHomeLineupGoalkeeper") var homeLineupGoalkeeper: String? = null,
    @SerializedName("strHomeLineupDefense") var homeLineupDefense: String? = null,
    @SerializedName("strHomeLineupMidfield") var homeLineupMidfield: String? = null,
    @SerializedName("strHomeLineupForward") var homeLineupForward: String? = null,
    @SerializedName("strHomeLineupSubstitutes") var homeLineupSubstitutes: String? = null,
    @SerializedName("strAwayGoalDetails") var awayGoalDetails: String? = null,
    @SerializedName("strAwayLineupGoalkeeper") var awayLineupGoalkeeper: String? = null,
    @SerializedName("strAwayLineupDefense") var awayLineupDefense: String? = null,
    @SerializedName("strAwayLineupMidfield") var awayLineupMidfield: String? = null,
    @SerializedName("strAwayLineupForward") var awayLineupForward: String? = null,
    @SerializedName("strAwayLineupSubstitutes") var awayLineupSubstitutes: String? = null
): Parcelable