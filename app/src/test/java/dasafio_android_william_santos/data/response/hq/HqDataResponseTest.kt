package com.example.desafio_android_william_santos.data.response.hq

import junit.framework.Assert.assertEquals
import org.junit.Test


class HqDataResponseTest {


    @Test
    fun testFilterGetHqResultHighestValue(){
        val price = PriceResponse(00.1)
        val price2 = PriceResponse(401.1)
        val price3 = PriceResponse(10.1)


        val hqResultHesponse = HqResultResponse("test","description",
            ThumbnailResponse("oi",".jpg"),
            arrayOf<PriceResponse>
                (price,price2,price3).asList())

        val price4 = PriceResponse(00.2)
        val price5 = PriceResponse(02.2)
        val price6 = PriceResponse(20.2)


        val hqResultHesponse1 = HqResultResponse("test","description",
            ThumbnailResponse("oi",".jpg"),
            arrayOf<PriceResponse>
                (price4,price5,price6).asList())
        val price7 = PriceResponse(00.3)
        val price8 = PriceResponse(03.3)
        val price9 = PriceResponse(30.3)


        val hqResultHesponse2 = HqResultResponse("test","description",
            ThumbnailResponse("oi",".jpg"),
            arrayOf<PriceResponse>
                (price7,price8,price9).asList())

        val hqDataResponse = HqDataResponse(arrayListOf(
            hqResultHesponse,
            hqResultHesponse1,
            hqResultHesponse2
        ))

        val modeHighesValue = hqDataResponse.getHqResultHighestValue().getHqModel()

        assertEquals(modeHighesValue.price,401.1)
    }

}