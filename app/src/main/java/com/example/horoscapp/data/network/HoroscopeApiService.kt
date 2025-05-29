package com.example.horoscapp.data.network

import com.example.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Con ésto y el PredictionResponse nos comunicamos con la Api y recuperamos información
 * aquí le indicamos que queremos de la Api
 */


interface HoroscopeApiService {

    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign: String): PredictionResponse

}