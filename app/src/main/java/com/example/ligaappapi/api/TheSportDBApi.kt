package com.example.ligaappapi.api

import com.example.ligaappapi.BuildConfig

object TheSportDBApi {
    fun getLeague(): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_leagues.php?s=soccer"
    }

    fun getMatch(idLeague: String?, typeMatch: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/" + typeMatch + "/?id=" + idLeague
    }

    fun getMatchDetail(idEvent: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=" + idEvent
    }

    fun getTeamDetail(idTeam: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + idTeam
    }

    fun searchMatch(event: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" + event
    }
}