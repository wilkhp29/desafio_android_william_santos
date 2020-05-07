package com.example.desafio_android_william_santos.data.results

import com.example.desafio_android_william_santos.data.model.Hq

sealed class HqResult {
    class Sucess(val hq:Hq):
        HqResult()
    object ServerError : HqResult()
    class ApiError(val code:Int):
        HqResult()
}