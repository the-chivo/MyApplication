package com.example.myapplication.Model

data class Categoria(
    val imagen : Int,
    val nombre : String,
    var productos : List<Producto>
)