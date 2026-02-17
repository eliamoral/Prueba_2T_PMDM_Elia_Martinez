package com.unirfp.app_retrofit_elia_martinez.data.remote

import com.unirfp.app_retrofit_elia_martinez.data.model.ProductoResponse
import retrofit2.http.GET

/**
 * Interfaz de Retrofit que define los puntos de acceso o endpoints de la API.
 */
interface ProductoApiService {
    // Definimos una petición GET a la ruta "api/products"
    // suspend indica que la función puede pausarse sin bloquear la app (usando corrutinas)
    @GET("api/products")
    suspend fun getProducts(): ProductoResponse
}
