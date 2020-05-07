package com.example.desafio_android_william_santos.data.response.hq

import com.example.desafio_android_william_santos.data.model.Hq
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HqResultResponse(
    @Json(name = "title")
    val title:String,
    @Json(name = "description")
    var description:String? = "null",
   @Json(name = "thumbnail")
    val thumbnail:ThumbnailResponse,
    @Json(name = "prices")
    val price:List<PriceResponse>
){
    fun getHqModel() = Hq(
        title = this.title,
        img = "${this.thumbnail.path}.${this.thumbnail.extension}",
        price = this.price.sortedWith(compareBy({it.let { prices -> prices.price.toDouble()}})).asReversed().get(0).price,
        description = this.description.toString()
    )
}
