package com.example.horoscapp.data.providers

import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {

    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            Aries,
            Aquario,
            Cancer,
            Capricornio,
            Escorpio,
            Geminis,
            Leo,
            Libra,
            Picis,
            Sagitario,
            Tauro,
            Virgo
        )
    }
}