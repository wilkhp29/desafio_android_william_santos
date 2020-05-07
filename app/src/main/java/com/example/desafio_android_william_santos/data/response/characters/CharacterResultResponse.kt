package com.example.desafio_android_william_santos.data.response.characters

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResultResponse(
    @Json(name = "id")
    val id:Int,
    @Json(name = "name")
    val name:String,
    @Json(name = "description")
    val description:String,
    @Json(name = "thumbnail")
    val thumbnail:ThumbnailResponse
)
