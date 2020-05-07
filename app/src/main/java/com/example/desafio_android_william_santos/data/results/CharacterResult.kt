package com.example.desafio_android_william_santos.data.results

import com.example.dasafio_android_william_santos.data.model.Character

sealed class CharacterResult {
    class Sucess(val characters:List<Character>):
        CharacterResult()
    object ServerError : CharacterResult()
    class ApiError(val code:Int):
        CharacterResult()
}