package com.example.desafio_android_william_santos.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface MarvelServices {
    @GET("users/{user}/repos")
   fun listRepos(): Call<List<>>
}