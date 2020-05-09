package com.example.desafio_android_william_santos.data.utils

import com.example.desafio_android_william_santos.data.Utils.HashGenerate.getHash
import org.junit.Assert.assertEquals
import org.junit.Test


class HashGenarate {
    @Test
    fun testHashIsValid(){
        val hashCaculator = getHash("testando o md5")
        assertEquals("abba85df25dc7b78c06929f088f6d742",hashCaculator)
    }

    @Test
    fun testHashDefault(){
        val hashCaculator = getHash()
        assertEquals("37fe7c3204f1ee2212808363719516c1",hashCaculator)
    }
}