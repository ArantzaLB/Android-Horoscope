package com.example.horoscapp.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Ésta clase es un interceptor de autentificación(en caso de que no se tenga en token
 * la aplicación se botaría del servidor), para que sea un interceptor debe
 * extender de éste e implementar sus funciones
 */


class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {

    /**
     * Basicamente a la cadena de datos que mandamos le agregamos (newBuilder()) un header que contenga
     * el token y le decimos que se comprima nuevamente (.build()) para luego proceder a retornarlo
     * con la nueva información
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder().header("Autorization", tokenManager.getToken()).build()
        return chain.proceed(request)
    }

}

class TokenManager @Inject constructor() {
    fun getToken(): String = "Matoi7845"
}