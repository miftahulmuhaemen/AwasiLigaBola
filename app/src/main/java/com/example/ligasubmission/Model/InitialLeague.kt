package com.example.ligasubmission.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InitialLeague(val id: Int?, val name: String?, val img: Int?) : Parcelable

