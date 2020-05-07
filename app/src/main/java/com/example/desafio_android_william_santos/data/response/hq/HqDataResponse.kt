package com.example.desafio_android_william_santos.data.response.hq

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HqDataResponse (
    @Json(name = "results")
    val hqList:List<HqResultResponse>
){
    fun getHqResultHighestValue(): HqResultResponse {
        val characterList = this.hqList.sortedWith(compareBy { it.let { hqResultResponse ->  hqResultResponse.getHqModel().price  } })
        return characterList.asReversed()[0]
    }
}
