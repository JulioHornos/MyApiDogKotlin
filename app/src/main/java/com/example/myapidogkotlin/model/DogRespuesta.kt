package com.example.myapidogkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DogRespuesta {
    @SerializedName("status")
    @Expose
    val status: String? = null

    @SerializedName("message")
    @Expose
    val images: List<String>? = null
}