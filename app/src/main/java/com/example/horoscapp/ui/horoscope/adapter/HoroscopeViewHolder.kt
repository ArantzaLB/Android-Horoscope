package com.example.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {

        //val context = binding.tvHorosscope.context

        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        //binding.tvHorosscope.text = context.getString(horoscopeInfo.name)
        binding.tvHorosscope.setText(horoscopeInfo.name)
        /**Cuando hagas clic en el objeto llama a la función startRotationAnimation(hace rotar la
         * view que se le proporcione) y la newLambd debe recibir otra función lambda onItemSelected
         * y pasale la inf del horoscopo del objeto, todo ésto de una lambda que llama a otra es
         * para que al terminar la animación se vaya a la pestaña correspondiente del simbolo del horoscopo
         */
        binding.parent.setOnClickListener {
            startRotationAnimation(
                binding.ivHoroscope,
                newLambda = { onItemSelected(horoscopeInfo) })
        }
    }

    //Creamos la función newLambda: () -> Unit, ésta sirve para llamar a otra función lamda onItemSelected
    private fun startRotationAnimation(view: View, newLambda: () -> Unit) {
        view.animate().apply {
            duration = 500
            //Indica la secuencia de la animación, si la velocidad se va a mantener constante o se
            //acelera
            interpolator = LinearInterpolator()
            rotationBy(360f)
            //Cuando termine la acción llama a la nueva función lamda
            withEndAction { newLambda() }
            start()
        }
    }
}