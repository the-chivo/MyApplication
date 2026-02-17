package com.example.myapplication.ViewModel

import com.example.myapplication.Model.Categoria
import com.example.myapplication.Model.Producto
import com.example.myapplication.R

object Categorias {

    private val herramientas = listOf(
        Producto(R.drawable.ic_launcher_background, "Destornillador", "$10", "$12"),
        Producto(R.drawable.ic_launcher_background, "Sierra", "$50", "$60")
    )

    private val herramientasElectricas= listOf(
        Producto(R.drawable.ic_launcher_background, "Radial", "$20", "$25")
    )

    var categorias = listOf(
        Categoria(R.drawable.ic_launcher_background, "Herramientas", herramientas),
        Categoria(R.drawable.ic_launcher_background, "Tornilleria", herramientasElectricas),
        Categoria(R.drawable.ic_launcher_background, "Otos", Productos.productos)
    )
}