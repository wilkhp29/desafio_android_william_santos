package com.example.desafio_android_william_santos.presentation.Hq

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.data.model.Hq
import com.example.desafio_android_william_santos.data.results.HqResult
import com.example.desafio_android_william_santos.repository.hq.HqReposiroty

class HQViewModel(val dataSource: HqReposiroty) : ViewModel() {
    val hqLiveData:MutableLiveData<Hq> = MutableLiveData()
    val viewFlipperLiveData:MutableLiveData<Pair<Int,Int?>> = MutableLiveData()

    fun getHqHighestValue(id:Int){
        dataSource.getHq(id){
            it.let { result ->
                when(result){
                    is HqResult.Sucess -> {
                        hqLiveData.value = result.hq
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_HQ,null)
                    }
                    is HqResult.ApiError -> {
                        if (result.code == 401){
                            viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERRO, R.string.error_401)
                        }else if(result.code == 200){
                            viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERRO, R.string.result_empty)
                        }
                        else{
                            viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERRO, R.string.error_400)
                        }
                    }
                    is HqResult.ServerError -> {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERRO,R.string.error_servidor)
                    }
                }
            }
        }
    }

    companion object{
        private const val VIEW_FLIPPER_HQ = 1
        private const val VIEW_FLIPPER_ERRO = 2
    }

    class ViewModelFactory(val dataSource: HqReposiroty) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(HQViewModel::class.java)){
                return  HQViewModel(dataSource) as T
            }
            throw  IllegalArgumentException("Unknown ViewmModel class")
        }

    }
}