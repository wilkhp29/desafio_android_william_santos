package com.example.desafio_android_william_santos.data.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.desafio_android_william_santos.data.Utils.HashGenerate.getHash
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HashGenarate {
    @Test
    fun testHashIsValid(){
        val hashCaculator = getHash("testando o md5")
        assertEquals("abba85df25dc7b78c06929f088f6d742",hashCaculator)
    }
}