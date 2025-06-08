package com.example.horoscapp.ui.horoscope

import com.example.horoscapp.data.providers.HoroscopeProvider
import com.example.horoscapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class HoroscopeViewModelTest {


    //Un mock nos ayuda a hacer pruebas al crear una especie de sustituto de la información que
    //requeriría cierta función para trabajar correctamente
    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel: HoroscopeViewModel

    //ésto sirve para decir si quiero hacer algo antes del test
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when viewModel is created then horoscopes are loaded`() {
        //Aquí le decimos que cada vez que se llame al .getHoroscopes(), retorne una lista que preparamos
        //en el motherObject, esto evita el error de que no reciba ningun valor
        every { horoscopeProvider.getHoroscopes() } returns horoscopeInfoList
        viewModel = HoroscopeViewModel(horoscopeProvider)

        //
        val horoscopes = viewModel.horoscope.value

        assertTrue(horoscopes.isNotEmpty())
    }

}

