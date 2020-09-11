package com.example.proyectofinal.model.database.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peliculas")
data class PeliculaEntidad(
    var popularity: Float,
    var poster_path: String?,
    @PrimaryKey
    var id: Int,
    var adult: Boolean,
    var backdrop_path: String?,
    var original_title: String,
    var overview: String?,
    var estrellas: Float? = null,
    var resena: String? = null
)