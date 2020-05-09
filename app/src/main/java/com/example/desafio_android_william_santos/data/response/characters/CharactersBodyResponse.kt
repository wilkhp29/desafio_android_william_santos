package com.example.desafio_android_william_santos.data.response.characters

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersBodyResponse (
    @Json(name = "data")
    val data:CharacterDataResponse?,
    @Json(name = "code")
    val code:Int?,
    @Json(name = "message")
    val message:String?
)