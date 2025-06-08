package com.example.horoscapp.data.network.response

import com.example.horoscapp.motherobject.HoroscopeMotherObject
import io.kotlintest.shouldBe
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class PredictionResponseTest {

    //Las `` ayudan a escribir el nombre de la función con espacio (solo lo podemos hacer en este capa)

    @Test
    fun `toDomain should return a correct PredictionModel`() {
        //Given (le doy lo que necesito)
        val horoscopeResponse = HoroscopeMotherObject.anyResponse

        //When (cuando ocurra la situación que estoy testeando)
        val predictionModel = horoscopeResponse.toDomain()

        //Then
        //Aquí le decimos que lo que esté en predictionModel debería ser igual a lo qu esté en horoscopeResponse
        predictionModel.sign shouldBe  horoscopeResponse.sign
        predictionModel.horoscope shouldBe  horoscopeResponse.horoscope
    }

}