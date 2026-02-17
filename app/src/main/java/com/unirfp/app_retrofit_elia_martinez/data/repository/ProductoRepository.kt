package com.unirfp.app_retrofit_elia_martinez.data.repository

import com.unirfp.app_retrofit_elia_martinez.data.model.Producto
import com.unirfp.app_retrofit_elia_martinez.data.remote.RetrofitInstance

/**
 * Repositorio que gestiona el acceso a los datos de productos.
 */
class ProductoRepository {
    suspend fun getProductos(): List<Producto> {
        return RetrofitInstance.api.getProducts().results
    }
}
