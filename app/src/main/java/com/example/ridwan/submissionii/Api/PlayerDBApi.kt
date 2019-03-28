package com.example.ridwan.submissionii.Api

import com.example.ridwan.submissionii.BuildConfig

object PlayerDBApi{
    fun getPlayer(id: String?):String {
        println("URL NYA "+ BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php?id="+id)
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php?id="+id
    }
}