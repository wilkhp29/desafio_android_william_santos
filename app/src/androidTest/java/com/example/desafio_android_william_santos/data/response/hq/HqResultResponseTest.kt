package com.example.desafio_android_william_santos.data.response.hq

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HqResultResponseTest {
    @Test
    fun testHighestValue(){
        val price = PriceResponse("valor1",00.1)
        val price2 = PriceResponse("valor2",01.1)
        val price3 = PriceResponse("valor3",10.1)


        val hqResultHesponse = HqResultResponse("test","description",
            ThumbnailResponse("oi",".jpg"),
            arrayListOf
       (price,price2,price3))

       val modelHq = hqResultHesponse.getHqModel();

        assertEquals(modelHq.price,10.1)
    }

    @Test
    fun testHighestValueOneInList(){
        val price = PriceResponse("valor1",00.1)


        val hqResultHesponse = HqResultResponse("test","description",
            ThumbnailResponse("oi",".jpg"),
            arrayListOf
                (price))

        val modelHq = hqResultHesponse.getHqModel();

        assertEquals(modelHq.price,00.1)

    }

    @Test
    fun testHighestValueZeroInList(){
        val price = PriceResponse("valor1", 0)
        val price2 = PriceResponse("valor2",01.1)
        val price3 = PriceResponse("valor3",10.1)


        val hqResultHesponse = HqResultResponse("test","description",
            ThumbnailResponse("oi",".jpg"),
            arrayListOf
                (price,price2,price3))

        val modelHq = hqResultHesponse.getHqModel();

        assertEquals(modelHq.price,10.1)
    }
}