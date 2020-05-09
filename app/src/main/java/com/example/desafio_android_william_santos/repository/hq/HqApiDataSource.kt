package com.example.desafio_android_william_santos.repository.hq


import com.example.desafio_android_william_santos.data.ApiServices
import com.example.desafio_android_william_santos.data.MarvelServices
import com.example.desafio_android_william_santos.data.response.hq.HqBodyResponse
import com.example.desafio_android_william_santos.data.results.HqResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HqApiDataSource(val services: MarvelServices =  ApiServices.service): HqReposiroty {

    override fun getHq(characterId: Int,HqResultCallback:(result: HqResult) -> Unit){
        services.getComics(characterId).enqueue(object: Callback<HqBodyResponse> {
            override fun onResponse(call: Call<HqBodyResponse>, response: Response<HqBodyResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {HqBodyResponse ->
                        if(HqBodyResponse.data.hqList.count() > 0){
                             val hq = HqBodyResponse.data.getHqResultHighestValue().getHqModel()
                            HqResultCallback(HqResult.Sucess(hq))
                        }else{
                            HqResultCallback(HqResult.ApiError(response.code()))
                        }

                    }
                }
                else{
                    HqResultCallback(HqResult.ApiError(response.code()))
                }
            }


            override fun onFailure(call: Call<HqBodyResponse>, t: Throwable) {
                HqResultCallback(HqResult.ServerError)
            }

        })
    }}