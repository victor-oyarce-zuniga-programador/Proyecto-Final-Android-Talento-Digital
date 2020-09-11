package com.example.proyectofinal.model

import com.example.proyectofinal.model.database.room.entidades.PeliculaEntidad

class PeliculaEntidadMapper {

    companion object{
        fun mapearDePeliculaAPeliculaEntidad(pelicula: Pelicula): PeliculaEntidad{
            return PeliculaEntidad(
                pelicula.popularity,
                pelicula.poster_path,
                pelicula.id,
                pelicula.adult,
                pelicula.backdrop_path,
                pelicula.original_title,
                pelicula.overview,
                pelicula.estrellas,
                pelicula.resena)
        }
    }
}