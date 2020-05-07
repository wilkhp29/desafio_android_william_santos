package com.example.desafio_android_william_santos.data.response.hq

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HqBodyResponse (
    @Json(name = "data")
    val data:HqDataResponse,
    @Json(name = "code")
    val code:Int
)