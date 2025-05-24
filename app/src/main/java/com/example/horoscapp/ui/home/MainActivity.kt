package com.example.horoscapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**-----------------------------TODO: PRIMERA EN SER CREADA--------------------------
 *Es la actividad contenedora que aloja el NavHostFragment, es decir, el punto de entrada visual
 * de tu app. Gracias a Navigation Component, se encarga de mostrar los distintos Fragments
 * (como el de la lista y el de detalles) dentro de un único layout.
 * Si bien fue creada primero, es necesario tener Navigation Component instalado y las fragments
 * por lo menos creados (aunque estén en blanco) así como el todo: main_graph y el botton_menu
 * todos esos elementos conforman la pantalla de inicio de la aplicación
 */
//Con ésto ahora puedo inyectarle cosas
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Permite acceder a la clase padre que controla todo lo de la navegación (no es creada por mi)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    //Inicia la interfaz de usuario
    private fun initUI() {
        initNavigation()
    }

    //Inicia la navegación
    private fun initNavigation() {
        /**
         * supportFragmentManager: accede al administrador de fragments de la actividad.
         * findFragmentById(R.id.fragmentContainerView): busca el fragmento contenedor que definiste
         * en el XML (por ejemplo, en activity_main.xml), que contiene la navegación.
         * as NavHostFragment: convierte el resultado al tipo NavHostFragment, que es el componente
         * especial encargado de mostrar los fragments asociados al nav_graph.
         */
        val navHost: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        /**
         * Aquí estás obteniendo el NavController, que es el objeto que realmente gestiona la
         * navegación entre fragments definidos en tu nav_graph.xml.
         */
        navController = navHost.navController

        /**
         * Conecta el BottomNavigationView con el NavController.
         * Esto hace que al pulsar un ícono del Bottom Navigation, se navegue automáticamente al
         * fragment correspondiente del nav_graph.
         */
        binding.bottomNavView.setupWithNavController(navController)
    }
}