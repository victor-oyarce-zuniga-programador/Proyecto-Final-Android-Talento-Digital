package com.example.proyectofinal.model

import java.io.Serializable
data class Pelicula(
    var popularity: Float,
    var poster_path: String?,
    var id: Int,
    var adult: Boolean,
    var backdrop_path: String?,
    var original_title: String,
    var overview: String?,
    var estrellas: Float? = null,
    var resena: String? = null
): Serializable