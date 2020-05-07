package com.example.desafio_android_william_santos.data

import com.example.desafio_android_william_santos.BuildConfig.keyPublic
import com.example.desafio_android_william_santos.BuildConfig.timestamp
import com.example.desafio_android_william_santos.data.Utils.HashGenerate
import com.example.desafio_android_william_santos.data.response.characters.CharactersBodyResponse
import retrofit2.Call

import retrofit2.http.GET

import retrofit2.http.Query


interface MarvelServices {
    @GET("characters")
   fun getCharacters(
        @Query("offset")offset:Int,
        @Query("limit")limit:Int,
        @Query("apikey")apikey: String = keyPublic,
        @Query("ts")ts: String = timestamp,
        @Query("hash")hash: String = HashGenerate.getHash()
    ): Call<CharactersBodyResponse>
}