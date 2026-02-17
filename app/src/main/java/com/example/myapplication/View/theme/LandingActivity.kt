package com.example.myapplication.View.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Model.Categoria
import com.example.myapplication.Model.Producto
import com.example.myapplication.R
import com.example.myapplication.ViewModel.Categorias
import com.example.myapplication.ViewModel.Productos
import kotlin.text.forEach

class LandingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }

    @Composable
    fun Estructura(navController: NavController) {

        var busqueda by remember { mutableStateOf("") }

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                NumberTextField(
                    label = "Buscar...",
                    value = busqueda,
                    onValueChange = { busqueda = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "filtro",
                    modifier = Modifier.size(45.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {

                item{
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "fondo landing",
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                items(Productos.productos.chunked(2)) { fila -> //fila = a lista de 2 producots
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        fila.forEach { producto ->
                            ProductoCard(producto, Modifier.weight(1f))
                            //aqui weight significa el peso de cada carta,
                            // si una columna el size es de 2, con un weight 1 significa que va a tomar la mitad
                        }

                        if (fila.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                            // y aqui quiere decir que si hay un producto el space tomara el espacio de otro
                        }
                    }
                }

                items(Categorias.categorias.chunked(3)){ fila ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        fila.forEach { categoria ->
                            CategoriasCard(
                                categoria = categoria,
                                onClick = {
                                    navController.navigate("categoria/${categoria.nombre}")
                                },
                                modifier = Modifier.weight(1f))
                        }
                        if (fila.size < 3){
                            repeat(3 - fila.size){
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun ProductoCard(producto: Producto, modifier: Modifier = Modifier) {

        Card(
            modifier = modifier
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(12.dp)
            ) {

                Image(
                    painter = painterResource(id = producto.imagen),
                    contentDescription = producto.nombre,
                    modifier = Modifier.size(140.dp)
                )

                Text(
                    text = producto.nombre,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = producto.precio,
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = producto.precioAnterior,
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough

                )
            }
        }
    }

    @Composable
    fun CategoriasCard(
        categoria: Categoria,
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        Card(
            modifier = modifier.padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            onClick = onClick
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = categoria.imagen),
                    contentDescription = categoria.nombre
                )
                Text(text = categoria.nombre)
            }
        }
    }


    @Composable
    fun NumberTextField(label : String, value : String, onValueChange : (String) -> Unit,modifier : Modifier = Modifier){//hacemos el proceso de abstraccion con esta funcion

        TextField(
            label = {
                Text(
                    text = label
                )
            },
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier,
        )
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun GreetingPreview() {    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            val navController = androidx.navigation.compose.rememberNavController()
            Estructura(navController = navController)
        }
    }
    }
}
