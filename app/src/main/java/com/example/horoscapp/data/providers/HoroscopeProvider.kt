package com.example.horoscapp.data.providers

import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject

/**
 * -----------------TODO:CREADO LUEGO DEL HOROSCOPEVIEWMODEL-------------------------
 * Sirve como proveedor de datos, en casos donde otra persona te proporciona la base de datos
 * desque aquí accedes a la lista y le proporcionas datos a toda la app según sea necesario
 */
class HoroscopeProvider @Inject constructor() {

    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            Aries,
            Aquarius,
            Cancer,
            Capricornio,
            Escorpio,
            Gemini,
            Leo,
            Libra,
            Picis,
            Sagitario,
            Taurus,
            Virgo
        )
    }
}