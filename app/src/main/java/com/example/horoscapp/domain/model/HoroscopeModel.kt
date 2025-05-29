package com.example.horoscapp.domain.model

/**
 * Ésta es una clase enumerada donde tenemos todos los signos del zodiaco
 *
 * Es una enum class (clase enumerada) en Kotlin. Las enumeraciones son tipos especiales
 * que contienen un conjunto fijo de constantes.
 *
 * Define los tipos únicos e inmutables de signos que existen en tu app.
 * Permite pasar información de manera segura entre pantallas/fragments
 * Facilita la comparación y el manejo lógico de los signos.
 *
 * De éste es donde se agarra para convertir el HoroscopeInfo y mandarle la String a la Api para que
 * busque la información necesaria
 */

enum class HoroscopeModel {
    Aries,
    Aquarius,
    Cancer,
    Capricorn,
    Scorpio,
    Gemini,
    Leo,
    Libra,
    Pisces,
    Sagittarius,
    Taurus,
    Virgo
}