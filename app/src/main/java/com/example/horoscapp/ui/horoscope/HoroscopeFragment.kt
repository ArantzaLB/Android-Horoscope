package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    /**Permite conectar el viewModel con el fragment
     * viewModels<HoroscopeViewModel>():Crear una instancia de HoroscopeViewModel asociada al ciclo
     *                              de vida del fragmento o actividad. O reutilizarla si ya existe
     * by: “usa lo que retorne viewModels<>() para inicializar esta propiedad”.
     */
    private val horoscopeViewMode by viewModels<HoroscopeViewModel>()

    /**
     * Declara una variable privada y opcional (nullable) para almacenar el binding.
     * Se llama con guion bajo _binding por convención, para diferenciarla de la propiedad pública.
     * Al inicio está en null porque el View del fragmento todavía no se ha creado.
     */
    private var _binding: FragmentHoroscopeBinding? = null

    /**
     * Crea una propiedad solo de lectura (val) llamada binding.
     * Cada vez que accedes a binding, estás accediendo a _binding!!
     * El !! es un force unwrap, que lanza una excepción si _binding es null.
     */
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Activamos el binding
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
