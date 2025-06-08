package com.example.horoscapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.horoscapp.R
import com.example.horoscapp.ui.detail.HoroscopeDetailActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//La anotación @HiltAndroidTest le dice a Hilt que esta clase necesita usar inyección de dependencias
//durante el test. Sin ella, Hilt no configura el entorno correctamente, y lanza esa excepción.
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest

class MainActivityTest {
    //es posible meter reglas a la hora de testear la UI, la de daggerHilt siempre debe ser la primera
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var mainActivityRule = ActivityScenarioRule(MainActivity::class.java)

    //Al usar dggerHilt o Intents, es nicesario iniciarlos aquí
    @Before
    fun setUp() {
        hiltRule.inject()
        Intents.init()
    }

    //Limpia los intents para que luego no de errores
    @After
    fun tearDown(){
        Intents.release()
    }


    /**
     * Este test verifica que al crear la MainActivity, el botón identificado como luckFragment
     * existe en pantalla y es clicable.
     * onView(withId(R.id.luckFragment)) → Busca un elemento en la vista con el ID luckFragment.
     * .perform(click()) → Simula un click sobre ese elemento (como si el usuario lo tocara).
     */
    @Test
    fun when_mainactivity_is_created_then_open_luck_fragment() {
        onView(withId(R.id.luckFragment)).perform(click())
    }

    /**
     * Este test valida que al hacer clic en el primer signo del horóscopo en un RecyclerView,
     * se abre la actividad HoroscopeDetailActivity.
     *
     * onView(withId(R.id.rvHoroscope)) → Busca el RecyclerView que contiene los signos del horóscopo.
     *
     * .perform(...) → Realiza una acción sobre el RecyclerView.
     *
     * RecyclerViewActions.actionOnItemAtPosition<...>(0, click()) → Hace clic en el elemento de la
     * posición 0 (el primer ítem del RecyclerView).
     *
     * intended(hasComponent(HoroscopeDetailActivity::class.java.name)) → Verifica que se haya
     * iniciado un Intent hacia HoroscopeDetailActivity.
     */

    @Test
    fun when_horoscope_is_selected_then_open_detail() {
        onView(withId(R.id.rvHoroscope)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        intended(hasComponent(HoroscopeDetailActivity::class.java.name))
    }
}