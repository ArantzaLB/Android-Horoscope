package com.example.horoscapp.motherobject

import com.example.horoscapp.data.network.response.PredictionResponse
import com.example.horoscapp.domain.model.HoroscopeInfo.Aquarius
import com.example.horoscapp.domain.model.HoroscopeInfo.Aries
import com.example.horoscapp.domain.model.HoroscopeInfo.Cancer
import com.example.horoscapp.domain.model.HoroscopeInfo.Capricornio
import com.example.horoscapp.domain.model.HoroscopeInfo.Escorpio
import com.example.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.example.horoscapp.domain.model.HoroscopeInfo.Leo
import com.example.horoscapp.domain.model.HoroscopeInfo.Libra
import com.example.horoscapp.domain.model.HoroscopeInfo.Picis
import com.example.horoscapp.domain.model.HoroscopeInfo.Sagitario
import com.example.horoscapp.domain.model.HoroscopeInfo.Taurus
import com.example.horoscapp.domain.model.HoroscopeInfo.Virgo

object HoroscopeMotherObject {

    val anyResponse = PredictionResponse("date", "prediction", "taurus")

    val horoscopeInfoList = listOf(
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