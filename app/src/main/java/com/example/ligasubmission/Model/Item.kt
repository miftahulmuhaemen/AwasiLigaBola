package com.example.ligasubmission.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item (val id: Int?, val name: String?, val desc: String?, val img: Int?):Parcelable