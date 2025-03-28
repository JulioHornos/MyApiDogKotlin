package com.example.myapidogkotlin.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPIService {

    @GET("images")
    suspend fun getFotosPerros(): Response<DogRespuesta>

    @GET("{raza}/images")
    suspend fun getDogs2(@Path("raza") raza: String): Response<DogRespuesta>
}

