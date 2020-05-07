package com.example.desafio_android_william_santos.data.response.characters

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThumbnailResponse (
    @Json(name = "path")
    val path:String,
    @Json(name = "extension")
    val extension:String
)
