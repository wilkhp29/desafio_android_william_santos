package com.example.desafio_android_william_santos.repository

import com.example.desafio_android_william_santos.data.CharacterResult

interface CharactersReposiroty {
    fun getCharacters(offset:Int,limit:Int,charactersResultCallback:(result: CharacterResult) -> Unit)
}