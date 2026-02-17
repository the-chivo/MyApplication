package com.example.myapplication.View.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.Model.Categoria
import com.example.myapplication.ViewModel.Categorias
import kotlin.text.forEach

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "LandingActivity"
    ) {
        composable("LandingActivity") {
            val activity = LandingActivity()
            activity.Estructura(navController)
        }

        composable(
            route = "categoria/{name}",
            arguments = listOf(
                navArgument(name = "name") {
                    type = NavType.StringType
                },
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")

            val categoriaSeleccionada = Categorias.categorias.find { it.nombre == name }

            if (categoriaSeleccionada != null) {
                DetailScreen(categoria = categoriaSeleccionada)
            } else {
                Text("Categoría no encontrada")
            }
        }
    }
}

@Composable
fun DetailScreen(categoria: Categoria) {
    val activity = LandingActivity()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Productos de: ${categoria.nombre}",
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categoria.productos.chunked(2)) { fila ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    fila.forEach { producto ->
                        activity.ProductoCard(producto, Modifier.weight(1f))
                    }
                    if (fila.size == 1) Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}