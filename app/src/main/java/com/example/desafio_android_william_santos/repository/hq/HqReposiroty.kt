package com.example.desafio_android_william_santos.repository.hq

import com.example.desafio_android_william_santos.data.results.HqResult

interface HqReposiroty {
    fun getHq(characterId:Int,HqResultCallback:(result: HqResult) -> Unit)
}