package com.example.desafio_android_william_santos.data

import com.example.desafio_android_william_santos.BuildConfig.Api
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiServices {
    private fun initRetrofit():Retrofit{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)


        val moshi:Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        return Retrofit.Builder()
            .baseUrl(Api)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val service = initRetrofit().create(MarvelServices::class.java)

}