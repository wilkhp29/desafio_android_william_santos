package com.example.desafio_android_william_santos.presentation.characters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dasafio_android_william_santos.data.model.Character
import com.example.desafio_android_william_santos.data.ApiServices
import com.example.desafio_android_william_santos.data.response.characters.CharactersBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersViewModel : ViewModel(){

    val characersLiveData:MutableLiveData<List<Character>> = MutableLiveData();

    fun getCharacter(){
        ApiServices.service.getCharacters().enqueue(object:Callback<CharactersBodyResponse>{

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


                    }

                    characersLiveData.value = characters
                }
            }


            override fun onFailure(call: Call<CharactersBodyResponse>, t: Throwable) {
                Log.d("erro",t.message)
            }



        })
    }

}