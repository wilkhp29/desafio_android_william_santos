package com.example.desafio_android_william_santos.data.response.hq

import junit.framework.Assert.assertEquals
import org.junit.Test


class HqResultResponseTest {
    @Test
    fun testHighestValue(){
        val price = PriceResponse(00.1)
        val price2 = PriceResponse(01.1)
        val price3 = PriceResponse(10.1)


        val hqResultHesponse = HqResultResponse("test","description",
            ThumbnailResponse("oi",".jpg"),
            arrayListOf
       (price,price2,price3))

       val modelHq = hqResultHesponse.getHqModel();

        assertEquals(modelHq.price,10.1)
    }

    @Test
    fun testHighestValueOneInList(){
        val price = PriceResponse(00.1)


        val hqResultHesponse = HqResultResponse("test","description",
            ThumbnailResponse("oi",".jpg"),
            arrayListOf
                (price))

        val modelHq = hqResultHesponse.getHqModel();

        assertEquals(modelHq.price,00.1)

    }

    @Test
    fun testHighestValueZeroInList(){
        val price = PriceResponse( 0)
        val price2 = PriceResponse(01.1)
        val price3 = PriceResponse(10.1)


        val hqResultHesponse = HqResultResponse("test","description",
            ThumbnailResponse("oi","jpg"),
            arrayListOf
                (price,price2,price3))

        val modelHq = hqResultHesponse.getHqModel();

        assertEquals(modelHq.title,"test")
        assertEquals(modelHq.description,"description")
        assertEquals(modelHq.price,10.1)
        assertEquals(modelHq.img,"oi.jpg")
    }

    @Test
    fun testDescriptionNull(){
        val price = PriceResponse( 0)
        val price2 = PriceResponse(01.1)
        val price3 = PriceResponse(10.1)


        val hqResultHesponse = HqResultResponse("test",null,
            ThumbnailResponse("oi","jpg"),
            arrayListOf
                (price,price2,price3))

        val modelHq = hqResultHesponse.getHqModel();

        assertEquals(modelHq.title,"test")
        assertEquals(modelHq.description," ")
        assertEquals(modelHq.price,10.1)
        assertEquals(modelHq.img,"oi.jpg")
    }


    @Test
    fun testThumbnail(){
        val thumb =  ThumbnailResponse("oi","jpg")

        assertEquals(thumb.extension,"jpg")
        assertEquals(thumb.path,"oi")
        assertEquals(thumb.getPhoto(),"oi.jpg")
    }

}