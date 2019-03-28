package com.example.ridwan.submissionii.Model

import com.google.gson.annotations.SerializedName


data class Player(
    @SerializedName("idPlayer")
    var playerId: String? = null,

    @SerializedName("strThumb")
    var playerBadge: String? = null,

    @SerializedName("strPlayer")
    var playerName: String? = null,

    @SerializedName("strPosition")
    var playerPosition: String? = null,

    @SerializedName("strHeight")
    var playerHeight: String? = null,

    @SerializedName("strWeight")
    var playerWeight: String? = null,

    @SerializedName("strDescriptionEN")
    var description: String? = null

)