package com.example.myapidogkotlin.model

import retrofit2.Response
import retrofit2.http.GET


interface DogAPIService {

    @GET("images")
    suspend fun getFotosPerros(): Response<DogRespuesta>
}