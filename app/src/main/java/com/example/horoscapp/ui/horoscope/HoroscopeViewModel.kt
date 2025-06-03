package com.example.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscapp.data.providers.HoroscopeProvider
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**---------TODO:Éste se crea a la par del fragment y tiene los siguientes usos:
 * Conservar datos durante cambios de configuración:
 * Si el Fragment se destruye y vuelve a crearse (por ejemplo, al rotar la pantalla), el ViewModel
 * sigue existiendo y mantiene los datos. Esto evita que tengas que volver a cargar o recalcular todo.
 *
 * Almacenar y gestionar datos para la UI:
 * El ViewModel actúa como una especie de almacén de datos para el Fragment. Puedes guardar ahí
 * listas, estados, índices seleccionados, flags, etc.
 *
 * Evitar lógica en la UI:
 * Así mantienes tu Fragment limpio, sin lógica de negocio ni acceso directo a datos, lo que
 * facilita pruebas, mantenimiento y escalabilidad.
 *
 * Facilitar la comunicación entre clases:
 * Puede funcionar como puente entre el Repository (o Provider, en tu caso) y la interfaz (Fragment),
 * proporcionando los datos ya preparados y observables con LiveData o StateFlow.
 */

/**
 * @Inject constructor() permite injectarle alguna clase, etc
 * : ViewModel() extiende la clase, de ésta manera nuestra clase tiene todas las propiedades de
 * un ViewModel
 */
@HiltViewModel
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider) : ViewModel() {

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
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }
}