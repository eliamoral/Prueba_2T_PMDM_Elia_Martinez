package com.unirfp.app_retrofit_elia_martinez.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.unirfp.app_retrofit_elia_martinez.data.model.Producto
import com.unirfp.app_retrofit_elia_martinez.data.repository.ProductoRepository

/**
 * ViewModel que gestiona la lógica de negocio y el estado de la pantalla de productos.
 */
class ProductViewModel : ViewModel() {

    private val repository = ProductoRepository()

    // Estado que contiene la lista de productos
    var productoList by mutableStateOf<List<Producto>>(emptyList())
        private set

    // Estado que indica si se están cargando los datos
    var isLoading by mutableStateOf(false)
        private set

    // Estado que contiene mensajes de error
    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchProductos()
    }

    /**
     * Función que realiza la petición para obtener los productos.
     */
    fun fetchProductos() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                // Uso de corrutinas para la llamada asíncrona
                val result = repository.getProductos()
                productoList = result
            } catch (e: Exception) {
                errorMessage = "Error al cargar productos: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}
