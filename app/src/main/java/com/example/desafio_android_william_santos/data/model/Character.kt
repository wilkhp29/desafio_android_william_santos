package com.example.dasafio_android_william_santos.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character (
    val id: Int,
    val name:String,
    val description:String,
    val img:String
): Parcelable