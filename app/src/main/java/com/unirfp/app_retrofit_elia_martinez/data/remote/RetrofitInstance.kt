package com.unirfp.app_retrofit_elia_martinez.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto Singleton que configura y proporciona la instancia de Retrofit.
 * Se usa object para asegurar que solo exista una instancia en toda la app.
 */
object RetrofitInstance {
    // La dirección principal
    private const val BASE_URL = "https://peticiones.online/"

    val api: ProductoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Convierte el JSON a objetos Kotlin automáticamente
            .build()
            .create(ProductoApiService::class.java)
    }
}
