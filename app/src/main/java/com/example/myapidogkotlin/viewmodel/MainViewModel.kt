package com.example.myapidogkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapidogkotlin.model.DogRespuesta
import com.example.myapidogkotlin.model.MainState
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val myEstado = MainState()
    private val _datos  = MutableLiveData<DogRespuesta>(DogRespuesta(null.toString(),ArrayList()))
    val datos: LiveData<DogRespuesta> get() = _datos

    fun devuelveFotos(raza: String){
        viewModelScope.launch {
            _datos.value = myEstado.recuperaFotos(raza)
        }
    }
}