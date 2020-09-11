package com.example.proyectofinal.model

data class RespuestaPelicula(
    var page:Int,
    var total_results: Int,
    var total_pages: Int,
    var results: List<Pelicula>)