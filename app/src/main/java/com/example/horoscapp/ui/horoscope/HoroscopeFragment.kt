package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import com.example.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

    //variable para poner el adapter y que se muestre algo en pantalla
    private lateinit var horoscopeAdapter: HoroscopeAdapter

    //Ésto es cuando se crea la vista
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Activamos el binding
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    //Ésto es cuando la vista ya ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    //Inicia la UI
    private fun initUi() {
        initRecyclerView()
        initUiState()
    }

    private fun initRecyclerView() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            Toast.makeText(context, getText(it.name), Toast.LENGTH_SHORT).show()
        })
        //Con ésto se aplican todas las configuraciones sin tener que escribir bindin. cada vez
        binding.rvHoroscope.apply {
            //layoutManager = LinearLayoutManager(context) indica el como muestra la lista
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
    }

    //Inicia el estado de la UI
    private fun initUiState() {
        //Ésto crea una corrutina, es especifica para los fragmens o activitys (se engancha a su ciclo de vida)
        lifecycleScope.launch {
            //Cuando empiece el ciclo de vida
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //Enganchate o collectea lo que hay en horoscope
                horoscopeViewMode.horoscope.collect {
                    //Cambios en horoscope (it es el nuevo listado)
                    horoscopeAdapter.updateList(it)
                }
            }
        }

    }
}
