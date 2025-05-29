package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.*
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**-------------------------TODO: PRIMERO EN SER MODIFICADO-----------------------------------
 * Al momento de ser configurado todo: el mainActivity
 * se crearon los tres fragments con sus respectivos activity y se les configuró el binding
 * así como su conexión con su viewModel.
 * En el caso de éste hace uso de un recycler view y debe ser configurado su Adapter y su respectiva
 * función lambda para navegar a HoroscopeDetailActivity con ayuda de Safe args y tras haber
 * hecho la conexión en el todo: main_graph (se hace la conección y ahí se crean las id de forma automatica)
 */

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    /**Permite conectar el viewModel con el fragment
     * viewModels<HoroscopeViewModel>():Crear una instancia de HoroscopeViewModel asociada al ciclo
     *                              de vida del fragmento o actividad. O reutilizarla si ya existe
     * by: “usa lo que retorne viewModels<>() para inicializar esta propiedad”.
     */
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

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

    //La función inicia el recycler view que muestra la lista
    private fun initRecyclerView() {
        //Aquí está la lamda
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            //Hacemos un when de tipo it(HoroscopeInfo), convertimos de HoroscpeInfo a HoroscopeModel
            val type = when (it) {
                Aquarius -> HoroscopeModel.Aquarius
                Aries -> HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricornio -> HoroscopeModel.Capricorn
                Escorpio -> HoroscopeModel.Scorpio
                Gemini -> HoroscopeModel.Gemini
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Picis -> HoroscopeModel.Pisces
                Sagitario -> HoroscopeModel.Sagittarius
                Taurus -> HoroscopeModel.Taurus
                Virgo -> HoroscopeModel.Virgo
            }
            /**
             * findNavController().navigate indica que navegue a lo que esté entre parentesis
             * ésto aparece gracias a el safe args, automáticamente crea la clase
             * HoroscopeFragmentDirections que permite acceder a la id que se creó al
             * darle una dirección hacia donde navegar en la vista del main_graph, además pide
             * una variable, en este caso de nombre type(almacena el when), ésto se le agregó desde
             * el main_graph al HoroscopeDetailActivity, de ésta manera al dar clic e ingresar
             * a la activity de detail ésta recibe el tipo de icono al que le diste clic y sabrá
             * que información cargar
             */
            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
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
                horoscopeViewModel.horoscope.collect {
                    //Cambios en horoscope (it es el nuevo listado)
                    horoscopeAdapter.updateList(it)
                }
            }
        }

    }
}
