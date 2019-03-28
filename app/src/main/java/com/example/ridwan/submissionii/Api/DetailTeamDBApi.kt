package com.example.ridwan.submissionii.Api

import com.example.ridwan.submissionii.BuildConfig

object DetailTeamDBApi{
    fun getTeam(id: String?):String {
        println("URL NYA : "+BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id="+id)
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id="+id
    }
}