package com.example.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {

    /**
     * Similar a lo hecho en el viewModel del Fragment, se crea un MutableStateFlow, en éste caso de tipo
     * HoroscopeDetailState, y se le asigna un estado inicial entre los parentesis
     */
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state

    lateinit var horoscope: HoroscopeModel

    fun getHoroscope(sing: HoroscopeModel) {
        horoscope = sing
        //Activa las corrutinas, por defecto sigue en el hilo principal
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading
            //Lo manda a un hilo secundario
            val result = withContext(Dispatchers.IO) { getPredictionUseCase(sing.name) }
            if (result != null) {
                _state.value = HoroscopeDetailState.Succes(result.horoscope, result.sign, horoscope)
            } else {
                _state.value =
                    HoroscopeDetailState.Error("Ha ocurrido un error, intentelo más tarde")
            }

        }
    }
}