package com.example.ridwan.submissionii.Api

import com.example.ridwan.submissionii.BuildConfig

object DetailMatchDBApi{
    fun getMatch(id: String?):String {
        println("URL NYA "+ BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id="+id)
//        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?s="+season+"&e="+name
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id="+id
    }
}