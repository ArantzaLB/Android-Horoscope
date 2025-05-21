package com.example.horoscapp.domain.model

import com.example.horoscapp.R

/**Sirve de base de datos
 * Cada signo del zodiaco será una subclase específica de esta clase, y llevará consigo su imagen
 * e identificador de nombre.
 */

sealed class HoroscopeInfo(val img: Int, val name: Int) {
    /**
     * Declara un objeto singleton llamado Aries.
     * Es una subclase de HoroscopeInfo.
     * Se le pasa un recurso de imagen (R.drawable.ic_horoscope) y un recurso de texto (R.string.aries).
     * Como es object, solo existe una instancia de Aries en toda la app.
     */
    data object Aries : HoroscopeInfo(R.drawable.aries, R.string.aries)
    data object Aquario : HoroscopeInfo(R.drawable.aquario, R.string.aquarius)
    data object Cancer : HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    data object Capricornio : HoroscopeInfo(R.drawable.capricornio, R.string.capricorn)
    data object Escorpio : HoroscopeInfo(R.drawable.escorpio, R.string.scorpio)
    data object Geminis : HoroscopeInfo(R.drawable.geminis, R.string.gemini)
    data object Leo : HoroscopeInfo(R.drawable.leo, R.string.leo)
    data object Libra : HoroscopeInfo(R.drawable.libra, R.string.libra)
    data object Picis : HoroscopeInfo(R.drawable.piscis, R.string.pisces)
    data object Sagitario : HoroscopeInfo(R.drawable.sagitario, R.string.sagittarius)
    data object Tauro : HoroscopeInfo(R.drawable.tauro, R.string.taurus)
    data object Virgo : HoroscopeInfo(R.drawable.virgo, R.string.virgo)

}