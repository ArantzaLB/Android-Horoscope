package com.example.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.model.HoroscopeModel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    //Permite usar el binding
    private lateinit var binding: ActivityHoroscopeDetailBinding

    //Permite conectar con el view model, es otra forma de escribirlo comparado con el de HoroscopeFagment
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    //Con ésto podemos decibir el type que mandamos desde el fragment
    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener { onBackPressed() }
    }


    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    //Ésta linea se va a llamar siempre que cambie el estado en el viewModel
                    when (it) {
                        is HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Succes -> succesState(it)
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }

    private fun succesState(state: HoroscopeDetailState.Succes) {
        binding.pb.isVisible = false
        binding.tvTitle.text = state.sing
        binding.tvBody.text = state.prediction

        val image = when (state.horoscopeModel) {
            Aries -> R.drawable.detail_aries
            Aquarius -> R.drawable.detail_aquarius
            Cancer -> R.drawable.detail_cancer
            Capricorn -> R.drawable.detail_capricorn
            Scorpio -> R.drawable.detail_scorpio
            Gemini -> R.drawable.detail_gemini
            Leo -> R.drawable.detail_leo
            Libra -> R.drawable.detail_libra
            Pisces -> R.drawable.detail_pisces
            Sagittarius -> R.drawable.detail_sagittarius
            Taurus -> R.drawable.detail_taurus
            Virgo -> R.drawable.detail_virgo
        }

        binding.ivDetail.setImageResource(image)
    }
}