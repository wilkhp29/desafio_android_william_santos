package com.example.desafio_android_william_santos.presentation.Hq

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_android_william_santos.data.model.Hq

class HQViewModel : ViewModel() {
    val hqLiveData:MutableLiveData<Hq> = MutableLiveData()

    fun getHqHighestValue(id:Int){
        hqLiveData.value = Hq("http://i.annihil.us/u/prod/marvel/i/mg/d/03/58dd080719806.jpg","Avengers: The Initiative (2007) #19","Join 3-D MAN, CLOUD 9, KOMODO, HARDBALL, and heroes around America in the battle that will decide the fate of the planet and the future of the Initiative program. Will the Kill Krew Army win the day?",9999999.20)
    }
}