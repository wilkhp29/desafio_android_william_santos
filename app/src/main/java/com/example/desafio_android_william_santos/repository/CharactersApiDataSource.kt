package com.example.desafio_android_william_santos.repository

import android.util.Log
import com.example.dasafio_android_william_santos.data.model.Character
import com.example.desafio_android_william_santos.data.ApiServices
import com.example.desafio_android_william_santos.data.CharacterResult
import com.example.desafio_android_william_santos.data.response.characters.CharactersBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersApiDataSource: CharactersReposiroty {
    override fun getCharacters(offset: Int, limit: Int,charactersResultCallback:(result:CharacterResult) -> Unit){
        ApiServices.service.getCharacters(offset,limit).enqueue(object: Callback<CharactersBodyResponse> {

            override fun onResponse(call: Call<CharactersBodyResponse>, response: Response<CharactersBodyResponse>) {
                if(response.isSuccessful){
                    val characters:MutableList<Character> = mutableListOf()

                    response.body()?.let {charactersBodyResponse ->
                        for(result in charactersBodyResponse.data.characterList){
                            val character = Character(
                                result.id,
                                result.name,
                                result.description,
                                "${result.thumbnail.path}.${result.thumbnail.extension}"
                            )

                            characters.add(character)
                        }
                        charactersResultCallback(CharacterResult.Sucess(characters))
                    }
                }
                else{
                    charactersResultCallback(CharacterResult.ApiError(response.code()))
                }
            }


            override fun onFailure(call: Call<CharactersBodyResponse>, t: Throwable) {
                charactersResultCallback(CharacterResult.ServerError)
            }



        })
    }}