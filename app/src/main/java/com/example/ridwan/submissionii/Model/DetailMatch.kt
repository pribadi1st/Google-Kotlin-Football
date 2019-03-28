package com.example.ridwan.submissionii.Model

import com.google.gson.annotations.SerializedName

data class DetailMatch(
    @SerializedName("dateEvent")
    val date : String? = null,

    @SerializedName("strHomeGoalDetails")
    val homeGoals: String? =null,

    @SerializedName("strAwayGoalDetails")
    val awayGoals: String? =null,

    @SerializedName("intHomeShots")
    val homeShots: String? =null,

    @SerializedName("intAwayShots")
    val awayShots: String? =null,

    @SerializedName("strHomeLineupGoalkeeper")
    val homeGK: String? = null,

    @SerializedName("strHomeLineupDefense")
    val homeDF: String? = null,

    @SerializedName("strHomeLineupMidfield")
    val homeMF: String? = null,

    @SerializedName("strHomeLineupForward")
    val homeFW: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    val homeSB: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    val awayGK: String? = null,

    @SerializedName("strAwayLineupDefense")
    val awayDF: String? = null,

    @SerializedName("strAwayLineupMidfield")
    val awayMF: String? = null,

    @SerializedName("strAwayLineupForward")
    val awayFW: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    val awaySB: String? = null,

    @SerializedName("intHomeScore")
    val homeScore: String? = null,

    @SerializedName("intAwayScore")
    val awayScore: String? = null
)