package com.example.ligasubmission.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class League(
    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("strLeagueAlternate")
    var strLeagueAlternate: String? = null,

    @SerializedName("intFormedYear")
    var intFormedYear: String? = null,

    @SerializedName("strGender")
    var strGender: String? = null,

    @SerializedName("strCountry")
    var strCountry: String? = null,

    @SerializedName("strWebsite")
    var strWebsite: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null,

    @SerializedName("strFanart1")
    var strFanart1: String? = null
) : Parcelable