package com.example.myapidogkotlin.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainState {
    var cadena = "https://dog.ceo/api/breed/"
    lateinit var misDatos : Datos
    lateinit var fotosPerrosCargado :  DogRespuesta

    suspend fun recuperaFotos(raza: String): Datos {
        val cadenaFinal = cadena +raza+"/"
        val retrofit = Retrofit.Builder()
            .baseUrl(cadenaFinal)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call = retrofit.create(DogAPIService::class.java).getFotosPerros()
        val fotosPerros = call.body()
        if(fotosPerros!=null){
            fotosPerrosCargado = fotosPerros
            if (fotosPerrosCargado.message!!.size> 10){
                var numPaginas :Int  = fotosPerrosCargado.message!!.size/10
                if (fotosPerrosCargado.message!!.size%10!=0) numPaginas++
                misDatos = Datos(fotosPerrosCargado.status, numPaginas, 1, mutableListOf())
                for (i in 0..9){
                    misDatos.message!!.add (fotosPerrosCargado.message!!.get(i))
                }
            }
            return misDatos
        }else{
            return Datos("no success",null, null, null )
        }
    }

    fun scrollFotos(misDatos: Datos): Datos{
        var inicio: Int
        var fin: Int
        inicio = misDatos.paginaActual!!*10
        misDatos.paginaActual = misDatos.paginaActual!! + 1
        if(misDatos.paginaActual!! < misDatos.numPaginas!!){
            fin  = (misDatos.paginaActual!! *10 - 1)
        }else{
            fin  = fotosPerrosCargado.message!!.size
        }
        for (i in inicio..fin){
            misDatos.message!!.add (fotosPerrosCargado.message!!.get(i))
        }
        return misDatos
    }
}

