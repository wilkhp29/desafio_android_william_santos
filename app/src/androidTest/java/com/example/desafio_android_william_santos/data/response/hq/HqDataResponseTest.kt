package com.example.desafio_android_william_santos.data.response.hq

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HqDataResponseTest {
    @Test
    fun testFilterGetHqResultHighestValue(){
        val price = PriceResponse("valor1",00.1)
        val price2 = PriceResponse("valor2",01.1)
        val price3 = PriceResponse("valor3",10.1)


        val hqResultHesponse = HqResultResponse("test","description",
            ThumbnailResponse("oi",".jpg"),
            arrayOf<PriceResponse>
                (price,price2,price3).asList())

        val price4 = PriceResponse("valor1",00.2)
        val price5 = PriceResponse("valor2",02.2)
        val price6 = PriceResponse("valor3",20.2)


        val hqResultHesponse1 = HqResultResponse("test","description",
            ThumbnailResponse("oi",".jpg"),
            arrayOf<PriceResponse>
                (price4,price5,price6).asList())
        val price7 = PriceResponse("valor1",00.3)
        val price8 = PriceResponse("valor2",03.3)
        val price9 = PriceResponse("valor3",30.3)


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

        assertEquals(modeHighesValue.price,30.3)
    }

}