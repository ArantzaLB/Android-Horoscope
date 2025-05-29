package com.example.horoscapp.data

import android.util.Log
import com.example.horoscapp.data.network.HoroscopeApiService
import com.example.horoscapp.data.network.response.PredictionResponse
import com.example.horoscapp.domain.Repository
import com.example.horoscapp.domain.model.PredictionModel
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Ésta clase ésta ligada a la Interfas Repository de domain,
 */

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {

    override suspend fun getPrediction(sing: String):PredictionModel? {
        //Petición retrofit
        //runCatching es una forma de ejecutar una tarea
        runCatching { apiService.getHoroscope(sing) }
            .onSuccess { return it.toDamain() } //Aquí convierto el Response a un modelo de la capa de dominio
            .onFailure { Log.i("arantza", "Ha ocurrido un error ${it.message}") }
        return null
    }
}