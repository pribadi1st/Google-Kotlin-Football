package com.example.ridwan.submissionii.DB

data class Favorite(val eventId:String?, val homeId: String? , val awayId: String?
                    , val homeScore:String? , val awayScore:String?,
               val homeName:String?,val awayName:String?, val date : String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
//        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_ID:String = "HOME_ID"
        const val AWAY_ID:String = "AWAY_ID"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_NAME: String = "HOME_NAME"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val DATE : String = "DATE"
    }
}