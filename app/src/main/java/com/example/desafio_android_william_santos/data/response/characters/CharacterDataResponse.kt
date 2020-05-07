package com.example.desafio_android_william_santos.data.response.characters

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataResponse (
    @Json(name = "results")
    val characterList:List<CharacterResultResponse>
)
