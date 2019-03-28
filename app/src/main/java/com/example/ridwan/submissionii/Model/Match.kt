package com.example.ridwan.submissionii.Model

import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("dateEvent")
    var eventDate: String? = null,

    @SerializedName("strHomeTeam")
    var home: String? = null,

    @SerializedName("strAwayTeam")
    var away: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,

    @SerializedName("strSeason")
    var season : String? = null,

    @SerializedName("idHomeTeam")
    var homeId : String? = null,

    @SerializedName("idAwayTeam")
    var awayId : String? = null
)