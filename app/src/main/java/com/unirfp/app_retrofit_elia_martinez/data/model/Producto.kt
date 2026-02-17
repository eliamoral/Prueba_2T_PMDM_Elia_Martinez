package com.unirfp.app_retrofit_elia_martinez.data.model

/**
 * Representa a un producto.
 * Esta clase define la estructura de los objetos que recibiremos de la API.
 */
data class Producto(
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val active: Boolean
)
