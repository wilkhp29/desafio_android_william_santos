package com.example.desafio_android_william_santos.repository.characters

import com.example.desafio_android_william_santos.data.results.CharacterResult

interface CharactersReposiroty {
    fun getCharacters(offset:Int,limit:Int,charactersResultCallback:(result: CharacterResult) -> Unit)
}