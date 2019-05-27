package com.example.ligasubmission.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Player(
    @SerializedName("idPlayer")
    var idPlayer: String? = null,

    @SerializedName("strNationality")
    var strNationality: String? = null,

    @SerializedName("strPlayer")
    var strPlayer: String? = null,

    @SerializedName("strTeamNational")
    var strTeamNational: String? = null,

    @SerializedName("dateBorn")
    var dateBorn: String? = null,

    @SerializedName("strNumber")
    var strNumber: String? = null,

    @SerializedName("dateSigned")
    var dateSigned: String? = null,

    @SerializedName("strSigning")
    var strSigning: String? = null,

    @SerializedName("strWage")
    var strWage: String? = null,

    @SerializedName("strPosition")
    var strPosition: String? = null,

    @SerializedName("strThumb")
    var strThumb: String? = null,

    @SerializedName("strBirthLocation")
    var strBirthLocation: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null

    ) : Parcelable