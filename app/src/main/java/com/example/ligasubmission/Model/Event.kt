package com.example.ligasubmission.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("strEvent")
    var strEvent: String? = null,

    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null,

    @SerializedName("intHomeScore")
    var intHomeScore: String? = null,

    @SerializedName("intRound")
    var intRound: String? = null,

    @SerializedName("intAwayScore")
    var intAwayScore: String? = null,

    @SerializedName("intSpectators")
    var intSpectators: String? = null,

    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String? = null,

    @SerializedName("strHomeRedCards")
    var strHomeRedCards: String? = null,

    @SerializedName("strHomeYellowCards")
    var strHomeYellowCards: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String? = null,

    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String? = null,

    @SerializedName("strHomeFormation")
    var strHomeFormation: String? = null,

    @SerializedName("strAwayRedCards")
    var strAwayRedCards: String? = null,

    @SerializedName("strAwayYellowCards")
    var strAwayYellowCards: String? = null,

    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String? = null,

    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String? = null,

    @SerializedName("strAwayFormation")
    var strAwayFormation: String? = null,

    @SerializedName("intHomeShots")
    var intHomeShots: String? = null,

    @SerializedName("intAwayShots")
    var intAwayShots: String? = null,

    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    @SerializedName("strDate")
    var strDate: String? = null,

    @SerializedName("strTime")
    var strTime: String? = null,

    @SerializedName("strThumb")
    var strThumb: String? = null
) : Parcelable