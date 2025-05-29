package com.example.horoscapp.data.network

import com.example.horoscapp.data.RepositoryImpl
import com.example.horoscapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Debido a que Retrofit es una librería y no podemos modificarla para poder usar daggerHilt
 * e inyectar dependencias en el Repository y conectarnos a la API, es necesario
 * crear o proveerlos desde aquí, así daggerHilt al buscar el objeto Retrofit, y no poder agarrarlo
 * desde la librería, vendrá aquí
 */


@Module //Lo convierte en un Modulo
@InstallIn(SingletonComponent::class) //Permite que todos puedan inyectar éste modulo
object NetworkModule {
    //Aquí me estoy proveyendo Retrofit
    @Provides
    @Singleton //evita que se esté creando constantemente un nuevo objeto
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        //Retornamos Retrofit que basicamente está haciendo la conexión a internet y la conversión del json
        return Retrofit
            .Builder()
            .baseUrl("https://newastro.vercel.app/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Un interceptor viene de una librería y es necesario ponerlo en el retrofit, es útil para
     * saber que éstas mandando en la llamada de internet a usar la api,
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    // Creamos una instancia del servicio Retrofit para hacer la llamada a la API
    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiService {
        return retrofit.create(HoroscopeApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: HoroscopeApiService): Repository {
        return RepositoryImpl(apiService)
    }
}