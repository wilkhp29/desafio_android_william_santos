package com.example.desafio_android_william_santos.presentation.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dasafio_android_william_santos.data.model.Character

class CharactersViewModel : ViewModel(){

    val herosLiveData:MutableLiveData<List<Character>> = MutableLiveData();

    fun getHeros(){
        herosLiveData.value = createFakeHeros();
    }

    fun createFakeHeros(): List<Character>{
        return listOf<Character>(
            Character(1,"william","develope"),
            Character(2,"william1","develope"),
            Character(3,"william2","develope"),
            Character(4,"william3","develope"),
            Character(5,"william4","develope")
        )
    }
}