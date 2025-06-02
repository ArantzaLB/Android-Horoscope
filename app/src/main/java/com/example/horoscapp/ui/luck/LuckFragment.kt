package com.example.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {


    /**
     * Declara una variable privada y opcional (nullable) para almacenar el binding.
     * Se llama con guion bajo _binding por convención, para diferenciarla de la propiedad pública.
     * Al inicio está en null porque el View del fragmento todavía no se ha creado.
     */
    private var _binding: FragmentLuckBinding? = null

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
        // Inflate the layout for this fragment
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.ivRoulette.setOnClickListener { spinRoulette() }
    }

    //Solo hace que gire la ruleta
    private fun spinRoulette() {
        /**val random = Random() ya no es necesario crear ésto con kotlin, en caso de java sí
         * Con ésto le decimos cuandos grados debe rotar, con nextInt le decimos a lo máximo que puede llegar
         * el +360 es en caso de que debe cero por lo menos estamos seguros de que dará 1 vuelta
         */
        val degrees: Int = Random.nextInt(1440) + 360

        //Aquí programamos la animación de rotación
        val animator: ValueAnimator =
            ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator =
            DecelerateInterpolator() //hace que la animación se detenga lentamente
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    //Aquí se crea la animación de deslizar la tarjeta cuando termina de rotar la ruleta
    private fun slideCard() {
        val slideUpAnimation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.ivRoulette.isVisible = true
            }
            //Cuando la a imación termina, hace crecer la carta llamando a una función
            override fun onAnimationEnd(animation: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

        binding.ivReverse.startAnimation(slideUpAnimation)
    }

    //Hace crecer la carta que sale de la ruleta
    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                //Al terminar la animación de que la carta crece en el centro se hace lo siguiente
                binding.ivReverse.isVisible = false //Equivale a poner la imagen en gone
                showPremonitionView()
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        binding.ivReverse.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {
        //AlphaAnimation(1.0f, 0.0f) es la opacidad, le decimos que pase de estar al 100(1.0f) a que esté en 0(0.0f)
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }


}