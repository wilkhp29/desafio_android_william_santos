package com.example.desafio_android_william_santos.presentation.heros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dasafio_android_william_santos.data.model.Hero

class HerosViewModel : ViewModel(){

    val herosLiveData:MutableLiveData<List<Hero>> = MutableLiveData();

    fun getHeros(){
        herosLiveData.value = createFakeHeros();
    }

    fun createFakeHeros(): List<Hero>{
        return listOf<Hero>(
            Hero(1,"william","develope"),
            Hero(2,"william1","develope"),
            Hero(3,"william2","develope"),
            Hero(4,"william3","develope"),
            Hero(5,"william4","develope")
        )
    }
}