package com.example.ridwan.submissionii.Api

import com.example.ridwan.submissionii.BuildConfig

object MatchDBApi{
    fun getMatch(type: String?, id: Int?):String {
        println("URL NYA "+ BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/" +type +".php?id="+id.toString())
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/" +type +".php?id="+id.toString()
    }
}