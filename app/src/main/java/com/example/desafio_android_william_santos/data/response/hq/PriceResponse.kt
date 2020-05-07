package com.example.desafio_android_william_santos.data.response.hq

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
 data class PriceResponse( @Json(name = "type")
                       val type:String,
                       @Json(name = "price")
                       val price: Double){

    constructor(@Json(name = "type")  type:String, @Json(name = "price") price: Int) : this(type,price.toDouble())
}
