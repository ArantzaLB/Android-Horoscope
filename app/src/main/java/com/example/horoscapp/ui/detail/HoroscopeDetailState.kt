package com.example.horoscapp.ui.detail

import com.example.horoscapp.domain.model.HoroscopeModel

/**
 * Ésta es una clase de estados en donde se le dice al detail que mostrar dependiendo del
 * estado, pude ser de erros, carga, que to-do funciona bien, en éste caso serán esos tres
 * pudo ser creada dentro del viewModel, pero es más ordenado crearla aparte
 */

sealed class HoroscopeDetailState {
    //Se usa un data object cuando no hay nada que pasarle
    data object Loading:HoroscopeDetailState()
    //Se usa una data class cuando se le deben pasar parametros
    data class Error(val error:String):HoroscopeDetailState()
    data class Succes(val prediction:String, val sing:String, val horoscopeModel: HoroscopeModel):HoroscopeDetailState()
}