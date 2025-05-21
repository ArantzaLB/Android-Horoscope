package com.example.horoscapp.ui.luck

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//Con ésto le ponemos daggerHilt
@HiltViewModel
/**
 * @Inject constructor() permite injectarle alguna clase, etc
 * : ViewModel() extiende la clase, de ésta manera nuestra clase tiene todas las propiedades de
 * un ViewModel
 */
class LuckViewModel @Inject constructor() : ViewModel() {

}