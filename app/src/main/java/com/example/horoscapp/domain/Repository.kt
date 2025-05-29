package com.example.horoscapp.domain

import com.example.horoscapp.domain.model.PredictionModel

/**
 * Debido a que es necesario que la capa de Data le pase información de la Api a la capa de Dominio
 * donde está el HoroscopeInfo, pero éstos no deben comunicarse directamente, es necesario crear un
 * Repository, éste sirve de puentre entre éstas dos capas.
 * Aquí definimos las cosas que necesitamos de data
 */

interface Repository {
    suspend fun getPrediction(sing:String):PredictionModel?
}