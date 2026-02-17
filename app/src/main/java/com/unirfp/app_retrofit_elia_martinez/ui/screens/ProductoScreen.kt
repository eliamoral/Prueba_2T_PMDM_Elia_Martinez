package com.unirfp.app_retrofit_elia_martinez.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.unirfp.app_retrofit_elia_martinez.presentation.viewmodel.ProductViewModel
import java.util.Locale


/**
 * Función Composable que define la interfaz de usuario de la lista de productos.
 * Vista en el patrón MVVM.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoScreen(viewModel: ProductViewModel = viewModel()) {
    val products = viewModel.productoList
    val loading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    // Estructura básica de la pantalla con barra superior
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Productos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray
                )
            )
        }
    ) { padding ->
        // Contenedor principal para organizar los elementos
        Box(modifier = Modifier.padding(padding)) {
            when {
                loading -> {
                    // Muestra un círculo de carga si todavía no han llegado los datos
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null -> {
                    // Muestra el mensaje de error si algo salió mal
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.fetchProductos() }) {
                            Text("Reintentar")
                        }
                    }
                }
                else -> {
                    // LazyColumn crea una lista desplazable eficiente
                    LazyColumn {
                        // Por cada producto en la lista, creamos una card
                        items(products) { product ->
                            ProductoCard(producto = product)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Composable que representa una tarjeta de cada producto.
 */
@Composable
fun ProductoCard(producto: com.unirfp.app_retrofit_elia_martinez.data.model.Producto) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Imagen del producto
            AsyncImage(
                model = producto.image,
                contentDescription = producto.name,
                modifier = Modifier
                    .size(120.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            // Información del producto
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = producto.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "€${String.format(Locale.US, "%.2f", producto.price)}",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Blue,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Categoría: ${
                        producto.category.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = producto.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )

                if (producto.active) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Surface(
                        color = Color.Green,
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = "Disponible",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
