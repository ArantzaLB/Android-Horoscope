package com.example.horoscapp.data.network.response

import com.example.horoscapp.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

/**
 * El PredictionResponse se encarga de recuperar los datos especificos de la API y le manda a
 * ApiService los que el solicite
 */

data class PredictionResponse(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String
){
    fun toDamain():PredictionModel{
        return PredictionModel(
            horoscope = horoscope,
            sign = sign
        )
    }
}