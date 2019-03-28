package com.example.ridwan.submissionii.Api

import com.example.ridwan.submissionii.BuildConfig

object LeagueDBApi{
    fun getListLeague():String {
        println("URL NYA "+ BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/all_leagues.php")
//        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?s="+season+"&e="+name
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/all_leagues.php"
    }

    fun getListTeam(id: String?):String {
        println("URL NYA "+ BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_teams.php?id="+id)
//        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?s="+season+"&e="+name
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/search_all_teams.php?l="+id
    }
}