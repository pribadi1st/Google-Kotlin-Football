package com.example.ridwan.submissionii.Model

import com.google.gson.annotations.SerializedName

data class DetailTeam(
    @SerializedName("strTeamBadge")
    val badge : String? = null,

    @SerializedName("strTeam")
    val name : String? = null,

    @SerializedName("strStadium")
    val stadium : String? = null,

    @SerializedName("intFormedYear")
    val formed : String? = null,

    @SerializedName("strDescriptionEN")
    val description : String? = null
)