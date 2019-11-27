package com.example.ligaappapi.db

data class FavoriteMatch(val id: Long?,
                         val eventId: String?,
                         val homeId: String?,
                         val awayId: String,
                         val homeName: String?,
                         val awayName: String?,
                         val homeScore: String?,
                         val awayScore: String?,
                         val dateEvent: String?){

    companion object{
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val HOME_NAME: String = "HOME_NAME"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val DATE_EVENT: String = "DATE_EVENT"
    }
}