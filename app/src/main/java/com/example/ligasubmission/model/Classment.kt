package com.example.ligasubmission.model

import com.google.gson.annotations.SerializedName


data class Classment(

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("teamid")
    var teamid: String? = null,

    @SerializedName("played")
    var played: String? = null,

    @SerializedName("goalsfor")
    var goalsfor: String? = null,

    @SerializedName("goalsagainst")
    var goalsagainst: String? = null,

    @SerializedName("goalsdifference")
    var goalsdifference: String? = null,

    @SerializedName("win")
    var win: String? = null,

    @SerializedName("draw")
    var draw: String? = null,

    @SerializedName("loss")
    var loss: String? = null,

    @SerializedName("total")
    var total: String? = null

)