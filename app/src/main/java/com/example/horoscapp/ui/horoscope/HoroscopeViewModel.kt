package com.example.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor() : ViewModel() {

    /**
     * Vacicamente estamos creando una lista mutable(que se puede modificar) que contiene
     * lo que esté en HoroscopeInfo, y para que no puedan midificarla 3ros, se crea un valor no mutable
     * que lo único que hace es tener acceso a la lista mutable para poder mostrarla
     */
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    /**
     * Éste metodo ya viene de base y es con el que se inicia el view model, aquí ya le pasamos
     *valores a esa lista vacia inicial
     */
    init {
        //Al valor del holder modificable le agregamos una lista
        _horoscope.value = listOf(
            Tauro, Aries, Geminis
        )
    }
}