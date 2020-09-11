package com.example.proyectofinal.model

data class Images(
    var base_url : String,
    var secure_base_url : String,
    var backdrop_sizes : List<String>,
    var poster_sizes : List<String>
)