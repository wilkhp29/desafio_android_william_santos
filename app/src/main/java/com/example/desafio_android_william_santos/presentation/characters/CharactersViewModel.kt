package com.example.desafio_android_william_santos.presentation.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dasafio_android_william_santos.data.model.Character
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.data.CharacterResult
import com.example.desafio_android_william_santos.repository.CharactersReposiroty
import java.lang.IllegalArgumentException

class CharactersViewModel(val dataSource: CharactersReposiroty) : ViewModel(){

    val characersLiveData:MutableLiveData<List<Character>> = MutableLiveData();
    val viewFlipperLiveData:MutableLiveData<Pair<Int,Int?>> = MutableLiveData();

    fun getCharacter(offset:Int = 0,limit:Int=20){

        dataSource.getCharacters(offset,limit){result:CharacterResult ->
            when(result){
                is CharacterResult.Sucess -> {
                    characersLiveData.value = result.characters
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_CHARACTER,null)
                }
                is CharacterResult.ApiError -> {
                    if (result.code == 401){
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERRO, R.string.charaters_error_401)
                    }else{
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERRO, R.string.charaters_error_400_generic)
                    }

                }
                is CharacterResult.ServerError -> {
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERRO,R.string.charaters_error_generic)
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