package com.example.horoscapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * La razon por la que se decidió poner en la capa de UI es las dos etiquetas que van antes del
 * valor, con ellas nos aseguramos que si bien se espera recibir un Int, éstos apuntaran a una imagen
 * de forma segura, evita errores o que se pase un entero cualquiera
 */
data class LuckyModel(
    @DrawableRes val image: Int,
    @StringRes val text: Int
)