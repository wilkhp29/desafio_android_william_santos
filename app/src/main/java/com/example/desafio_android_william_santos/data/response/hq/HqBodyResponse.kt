package com.example.desafio_android_william_santos.data.response.hq

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HqBodyResponse (
    @Json(name = "data")
    val data:HqDataResponse? = null,
    @Json(name = "code")
    val code:Int?= null,
    @Json(name="message")
    val message:String? = null
)