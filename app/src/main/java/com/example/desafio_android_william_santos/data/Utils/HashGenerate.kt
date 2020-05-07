package com.example.desafio_android_william_santos.data.Utils

import com.example.desafio_android_william_santos.BuildConfig.timestamp
import com.example.desafio_android_william_santos.BuildConfig.keyPublic
import com.example.desafio_android_william_santos.BuildConfig.keyPrivate
import java.lang.Exception
import java.lang.StringBuilder
import java.security.MessageDigest


object HashGenerate {
    private fun byteArrayToHexString(array:Array<Byte>):String{
        var result = StringBuilder(array.size*2)

        for (byte in array){
            val toAppend = String.format("%2X",byte).replace(" ","0")
            result.append(toAppend)
        }

        return  result.toString().toLowerCase()
    }

     fun getHash(text:String = "${timestamp}${keyPrivate}${keyPublic}"): String {
         var result = ""
         try {
             val md5 = MessageDigest.getInstance("MD5")
             val md5HashBytes = md5.digest(text.toByteArray()).toTypedArray()
             result = byteArrayToHexString(md5HashBytes)
         }catch (e:Exception){
             result = "error: ${e.message}"
         }
         return result
    }
}