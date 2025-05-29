package com.example.horoscapp.domain.model

/**
 * Debido a que no es una buena prática pasarle directamnte el PredictionResponse con to-do lo de
 * las librerias, es necesario crearle su model en la capa de de domain y en el Response crear
 * la función que le pase la información a ésde model
 */
data class PredictionModel (
    val horoscope: String,
    val sign: String
)