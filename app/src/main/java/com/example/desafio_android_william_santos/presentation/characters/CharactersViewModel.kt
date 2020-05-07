package com.example.desafio_android_william_santos.presentation.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dasafio_android_william_santos.data.model.Character
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.data.results.CharacterResult
import com.example.desafio_android_william_santos.repository.characters.CharactersReposiroty
import java.lang.IllegalArgumentException
import java.util.*

class CharactersViewModel(val dataSource: CharactersReposiroty) : ViewModel(){

    val characersLiveData:MutableLiveData<List<Character>> = MutableLiveData();
    val viewFlipperLiveData:MutableLiveData<Pair<Int,Int?>> = MutableLiveData();
    private val characters:MutableList<Character> = ArrayList()
    fun getCharacter(offset:Int = 0,limit:Int=20){

        dataSource.getCharacters(offset,limit){result: CharacterResult ->
            when(result){
                is CharacterResult.Sucess -> {
                    characters.addAll(result.characters)
                    characersLiveData.value =  characters
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_CHARACTER,null)
                }
                is CharacterResult.ApiError -> {
                    if (result.code == 401){
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERRO, R.string.error_401)
                    }else{
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERRO, R.string.error_400)
                    }

                }
                is CharacterResult.ServerError -> {
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERRO,R.string.error_servidor)
                }
            }
        }
    }


    companion object{
        private const val VIEW_FLIPPER_CHARACTER = 1
        private const val VIEW_FLIPPER_ERRO = 2
    }

    class ViewModelFactory(val dataSource: CharactersReposiroty) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
           if(modelClass.isAssignableFrom(CharactersViewModel::class.java)){
               return  CharactersViewModel(dataSource) as T
           }
            throw  IllegalArgumentException("Unknown ViewmModel class")
        }

    }
}