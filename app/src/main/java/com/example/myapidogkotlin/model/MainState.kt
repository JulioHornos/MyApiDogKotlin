package com.example.myapidogkotlin.model

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainState {
    var cadena = "https://dog.ceo/api/breed/"

    suspend fun recuperaFotos(raza: String): Datos {
        cadena = cadena +raza+"/"
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .build()

        val call = retrofit.create(DogAPIService::class.java).getFotosPerros()
        val fotosPerros = call.body()
        if(fotosPerros!=null){
            return Datos(fotosPerros.status.toString(), fotosPerros.images )
        }else{
            return Datos("no success", null )
        }
    }
}