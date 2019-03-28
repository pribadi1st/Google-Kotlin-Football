package com.example.ridwan.submissionii.Api

import com.example.ridwan.submissionii.BuildConfig

object SearchDBApi{
    fun getTeam(query: String?):String {
        println("URL NYA "+ BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php?t="+query)
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php?t="+query
    }

    fun getMatch(query: String?):String {
        println("URL NYA "+ BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?e="+query)
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?e="+query
    }
}