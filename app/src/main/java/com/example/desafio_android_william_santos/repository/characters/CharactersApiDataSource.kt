package com.example.desafio_android_william_santos.repository.characters

import com.example.dasafio_android_william_santos.data.model.Character
import com.example.desafio_android_william_santos.data.ApiServices
import com.example.desafio_android_william_santos.data.MarvelServices
import com.example.desafio_android_william_santos.data.results.CharacterResult
import com.example.desafio_android_william_santos.data.response.characters.CharactersBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersApiDataSource(val service:MarvelServices = ApiServices.service): CharactersReposiroty {

    override fun getCharacters(offset: Int, limit: Int,charactersResultCallback:(result: CharacterResult) -> Unit){
        service.getCharacters(offset,limit).enqueue(object: Callback<CharactersBodyResponse> {

            override fun onResponse(call: Call<CharactersBodyResponse>, response: Response<CharactersBodyResponse>) {
                if(response.isSuccessful){
                    val characters:MutableList<Character> = mutableListOf()

                    response.body()?.let {charactersBodyResponse ->
                        for(result in charactersBodyResponse.data.characterList){
                            val character = result.getCharacterModel()
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